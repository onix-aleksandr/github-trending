package com.app.githubtrending.data.entity.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiRepositories {
    @SerializedName("items")
    private List<ApiRepository> details;

    public List<ApiRepository> getItems() {
        return details;
    }
}
