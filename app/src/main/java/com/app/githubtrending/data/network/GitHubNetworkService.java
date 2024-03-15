package com.app.githubtrending.data.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubNetworkService {

    private static final String BASE_URL = "https://api.github.com/";

    public GitHubApiService getGithubApiService() {
        return githubApiService;
    }

    private final GitHubApiService githubApiService;

    public GitHubNetworkService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(loggingInterceptor);

        httpClientBuilder.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("X-GitHub-Api-Version", "2022-11-28")
                .method(originalRequest.method(), originalRequest.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        OkHttpClient httpClient = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build();

        githubApiService = retrofit.create(GitHubApiService.class);
    }
}
