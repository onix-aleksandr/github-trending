package com.app.githubtrending.data.entity.network;

import com.google.gson.annotations.SerializedName;

public class ApiRepositoryOwner {
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
