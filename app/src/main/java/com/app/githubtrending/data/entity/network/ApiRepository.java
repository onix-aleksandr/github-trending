package com.app.githubtrending.data.entity.network;

import com.google.gson.annotations.SerializedName;

public class ApiRepository {
    @SerializedName("id")
    private long id;
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @SerializedName("forks")
    private long forks;
    @SerializedName("html_url")
    private String url;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("owner")
    private ApiRepositoryOwner owner;
    @SerializedName("forks_count")
    private long forksCount;
    @SerializedName("language")
    private String language;

    public long getId() {
        return id;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public long getForks() {
        return forks;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ApiRepositoryOwner getOwner() {
        return owner;
    }

    public long getForksCount() {
        return forksCount;
    }

    public String getLanguage() {
        return language;
    }
}
