package com.tulik15b.topgitrepositories.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.tulik15b.topgitrepositories.R;
import com.tulik15b.topgitrepositories.model.TGRepo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, new TopGitRepoListFragment(), "TopGitRepoListFragment");
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void displayRepoDetails(TGRepo repo){
        TopGitRepoItemFragment topGitRepoItemFragment = new TopGitRepoItemFragment();
        Bundle bundle = new Bundle();
        //bundle.putInt("REPO_ID",repo.id);
        bundle.putString("REPO_NAME", repo.name);
        topGitRepoItemFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, topGitRepoItemFragment, null).commit();
    }
}
