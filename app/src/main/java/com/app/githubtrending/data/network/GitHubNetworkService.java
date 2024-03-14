package com.app.githubtrending.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubNetworkService {

    private static final String BASE_URL = "https://api.github.com/";

    public GitHubApiService getGithubApiService() {
        return githubApiService;
    }

    private final GitHubApiService githubApiService;

    public GitHubNetworkService() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        githubApiService = retrofit.create(GitHubApiService.class);
    }
}
