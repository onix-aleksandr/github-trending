package com.app.githubtrending.data.repository;

import androidx.annotation.NonNull;

import com.app.githubtrending.data.entity.NetworkCallback;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.data.entity.network.ApiRepositories;
import com.app.githubtrending.data.entity.network.ApiRepository;
import com.app.githubtrending.data.network.GitHubNetworkService;
import com.app.githubtrending.data.repository.params.TrendingRepositoriesParams;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingRepository {

    private final GitHubNetworkService service = new GitHubNetworkService();

    private static final String SEARCH_ORDER = "desc";
    private static final String SEARCH_SORT = "stars";


    public void searchRepositories(TrendingRepositoriesParams params, NetworkCallback<List<Repository>> callback) {
        Call<ApiRepositories> call = service.getGithubApiService().searchRepositories(
            params.buildQuery(),
            SEARCH_SORT,
            SEARCH_ORDER,
            params.perPage(),
            params.page());

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ApiRepositories> call, @NonNull Response<ApiRepositories> response) {
                ApiRepositories repos = response.body();
                if (repos != null && repos.getItems() != null && !repos.getItems().isEmpty()) {
                    mapRepositories(repos, callback);
                } else {
                    callback.onError(new Throwable("Server error"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiRepositories> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    private void mapRepositories(ApiRepositories repos, NetworkCallback<List<Repository>> callback) {
        List<ApiRepository> apiRepositories = repos.getItems();
        List<Repository> repositories = new ArrayList<>();

        for (ApiRepository apiRepository : apiRepositories) {
            repositories.add(new Repository(apiRepository));
        }

        callback.onSuccess(repositories);
    }
}
