package com.app.githubtrending.data.entity;

public interface NetworkCallback<T> {
    void onSuccess(T data);

    void onError(Throwable error);
}
