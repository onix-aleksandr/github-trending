package com.app.githubtrending.data.network;

import com.app.githubtrending.data.entity.network.ApiRepositories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApiService {
    @GET("search/repositories")
    Call<ApiRepositories> searchRepositories(
        @Query(value = "q", encoded = true) String query,
        @Query("sort") String sort,
        @Query("order") String order,
        @Query("per_page") int perPage,
        @Query("page") int page
    );
}
