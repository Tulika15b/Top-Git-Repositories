package com.tulik15b.topgitrepositories.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "git_repo_table")
public class TGRepo{

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @NonNull
    public String language;

    @SerializedName("username")
    public String username;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("url")
    public String url;

    @SerializedName("avatar")
    public String avatarUrl;

    /*@SerializedName("repo")
    public List<Repo> repo;


    public class Repo {

        @SerializedName("avatar")
        public String repoName;

        @SerializedName("description")
        public String repoDescription;

        @SerializedName("url")
        public String repoUrl;

    }*/
}