package com.tulik15b.topgitrepositories.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tulik15b.topgitrepositories.model.TGRepo;
import com.tulik15b.topgitrepositories.repository.TopGitRepository;

import java.util.List;

public class TopGitRepoViewModel extends AndroidViewModel {

    private TopGitRepository mTopGitRepository;
    private LiveData<List<TGRepo>> mRepoList;


    public TopGitRepoViewModel(@NonNull Application application, String language) {

        super(application);
        mTopGitRepository = new TopGitRepository(application);

    }

    public LiveData<TGRepo> getRepoFromRepoId(String id){
        return mTopGitRepository.getRepoFromRepoId(id);
    }

    public LiveData<List<TGRepo>> getmRepoList(String language) {

        Log.d("TGRVM", "Fetching RepoList lang wise");
        mRepoList = mTopGitRepository.getAllRepos(language);
        return mRepoList;
    }

    public LiveData<List<TGRepo>> getSearchResultList(String language, String query){
        return mTopGitRepository.getSearchRepos(language, query);
    }

    public void insertAllRecordsToDao(List<TGRepo> repoList, String language){

        if(repoList != null){
            Log.d("TGR","Inserting record 1-1 in dao");
            for(TGRepo repo : repoList){
                repo.language = language;
                mTopGitRepository.insertRecord(repo);
            }

        }

    }

    public void insertRepoRecord(TGRepo word) {
        mTopGitRepository.insertRecord(word);
    }

    public void clearAll(){
        mTopGitRepository.deleteAll();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String language;

        public Factory(@NonNull Application application, String language) {
            this.application = application;
            this.language = language;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TopGitRepoViewModel(application, language);
        }
    }

}


