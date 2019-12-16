package com.tulik15b.topgitrepositories.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tulik15b.topgitrepositories.R;
import com.tulik15b.topgitrepositories.adapter.TGRepoAdapter;
import com.tulik15b.topgitrepositories.model.TGRepo;
import com.tulik15b.topgitrepositories.util.TGRepoCommonUtils;
import com.tulik15b.topgitrepositories.viewmodel.TopGitRepoViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopGitRepoListFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {

    public static final String TAG = "TopGitRepoListFragment";
    public TGRepoAdapter repoListAdapter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TopGitRepoViewModel mViewModel;

    Button fetchBtn;
    EditText languageFilter;
    String language;

    SearchView searchView;

    List<TGRepo> mRepoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_tg_repo_list, container, false);

        mRepoList = new ArrayList<>();
        languageFilter = parentView.findViewById(R.id.ev_language);

        fetchBtn = parentView.findViewById(R.id.btn_fetch_data);
        fetchBtn.setOnClickListener(this);

        searchView = parentView.findViewById(R.id.sv_search_text);
        searchView.setOnQueryTextListener(this);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView = parentView.findViewById(R.id.repo_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        repoListAdapter = new TGRepoAdapter(getContext(), this);
        recyclerView.setAdapter(repoListAdapter);


        return parentView;

    }

    @Override
    public void onStart() {
        super.onStart();

        if(!TextUtils.isEmpty(language))
        fetchRepositoryData();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_fetch_data :
                fetchRepositoryData();
                break;
        }
    }

    void  fetchRepositoryData(){
        language = languageFilter.getText().toString();
        Log.d("TGR", "LANGUAGE ::" + language);
        TopGitRepoViewModel.Factory factory = new TopGitRepoViewModel.Factory(getActivity().getApplication(), language);
        mViewModel = ViewModelProviders.of(getActivity(), factory).get(TopGitRepoViewModel.class);

        mViewModel.getmRepoList(languageFilter.getText().toString()).observe(this, new Observer<List<TGRepo>>() {

            @Override
            public void onChanged(@Nullable final List<TGRepo> repoList) {

                repoListAdapter.setTrendingList(repoList);
                mRepoList = repoList;
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(mRepoList != null){
            TGRepo repo = mRepoList.get(position);
            Log.d("TGR", "repoNAme ::" + repo.name);
            if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED))
                ((MainActivity)getActivity()).displayRepoDetails(repo);
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        mViewModel.getSearchResultList(language, searchView.getQuery().toString()).observe(this, new Observer<List<TGRepo>>() {

            @Override
            public void onChanged(@Nullable final List<TGRepo> repoList) {
                repoListAdapter.setTrendingList(repoList);
            }
        });
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
