package com.tulik15b.topgitrepositories.repository;

import com.tulik15b.topgitrepositories.model.TGRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TopGitRepoWebService {

    @GET("/developers?since=weekly")
    Call<List<TGRepo>> getProjectList(@Query("language") String language);

}
