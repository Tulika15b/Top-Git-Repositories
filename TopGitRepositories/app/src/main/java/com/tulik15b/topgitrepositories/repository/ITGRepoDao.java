package com.tulik15b.topgitrepositories.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tulik15b.topgitrepositories.model.TGRepo;

import java.util.List;

@Dao
public interface ITGRepoDao {

    String BASE_URL = "https://github-trending-api.now.sh/";

    @Query("SELECT * from git_repo_table WHERE language == :language")
    List<TGRepo> fetchAllRepositories(String language);

    @Query("SELECT * from git_repo_table WHERE name == :name")
    LiveData<TGRepo> fetchRepoFromName(String name);

    @Query("SELECT * from git_repo_table WHERE name == :query OR username == :query")
    LiveData<List<TGRepo>> fetchSearchedRepos(String query);

    @Query("DELETE FROM git_repo_table")
    void deleteAll();

    @Insert
    void insertRecords(TGRepo repos);


}
