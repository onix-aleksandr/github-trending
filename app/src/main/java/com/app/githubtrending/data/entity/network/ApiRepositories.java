package com.app.githubtrending.data.entity.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiRepositories {
    @SerializedName("items")
    private ArrayList<ApiRepository> details;

    public ArrayList<ApiRepository> getItems() {
        return details;
    }
}
