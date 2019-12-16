package com.tulik15b.topgitrepositories.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tulik15b.topgitrepositories.model.TGRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopGitRepository {

    private ITGRepoDao mTopGitDao;
    private TopGitRepoWebService mTopGitWebService;
    private Application application;

    public TopGitRepository(Application application) {
        TGRepoRoomDataBase db = TGRepoRoomDataBase.getDatabase(application);
        mTopGitDao = db.gitDao();
        this.application = application;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mTopGitDao.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTopGitWebService = retrofit.create(TopGitRepoWebService.class);

    }

    public LiveData<List<TGRepo>> getProjectList(final String language) {
        Log.d("TGR", "Fetching RepoList lang wise from retrofit");
        final MutableLiveData<List<TGRepo>> data = new MutableLiveData<>();

        mTopGitWebService.getProjectList(language).enqueue(new Callback<List<TGRepo>>() {
            @Override
            public void onResponse(Call<List<TGRepo>> call, Response<List<TGRepo>> response) {
                data.setValue(response.body());

                for(TGRepo repo : response.body()){
                    repo.language = language;
                    mTopGitDao.insertRecords(repo);
                }

            }

            @Override
            public void onFailure(Call<List<TGRepo>> call, Throwable t) {
                Log.d("TGR", "Failed to sync, checking if i have some data in db for this language");
                data.setValue(mTopGitDao.fetchAllRepositories(language));
            }

        });

        return data;
    }


    public LiveData<List<TGRepo>> getAllRepos(String language) {

        Log.d("TGRVM", "Fetching RepoList lang wise");
        LiveData<List<TGRepo>> mResponseList = getProjectList(language);
        return mResponseList;

    }

    public LiveData<List<TGRepo>> getSearchRepos(String language, String query){
       return mTopGitDao.fetchSearchedRepos(query);
    }

    public LiveData<TGRepo> getRepoFromRepoId(String name){
        return mTopGitDao.fetchRepoFromName(name);
    }

    public void insertRecord (TGRepo repo) {

        new insertAsyncTask(mTopGitDao).execute(repo);
    }

    public void deleteAll(){
        new deleteAsyncTask(mTopGitDao).execute();
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private ITGRepoDao mAsyncTaskDao;

        deleteAsyncTask(ITGRepoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


    private static class insertAsyncTask extends AsyncTask<TGRepo, Void, Void> {

        private ITGRepoDao mAsyncTaskDao;

        insertAsyncTask(ITGRepoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TGRepo... params) {
            mAsyncTaskDao.insertRecords(params[0]);
            return null;
        }
    }

}
