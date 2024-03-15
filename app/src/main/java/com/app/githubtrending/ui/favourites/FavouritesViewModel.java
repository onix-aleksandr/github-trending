package com.app.githubtrending.ui.favourites;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.githubtrending.data.database.AppDatabase;
import com.app.githubtrending.data.database.RepositoriesDao;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.data.entity.database.RepositoryEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritesViewModel extends ViewModel {

    private RepositoriesDao repositoriesDao;

    MutableLiveData<List<Repository>> repositories = new MutableLiveData<>();

    public void setupDatabase(AppDatabase database) {
        repositoriesDao = database.userDao();
    }

    @SuppressLint("CheckResult")
    public void fetchFavourites() {
        repositoriesDao.getFavourites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(repositoryEntities -> {
                ArrayList<Repository> repositories = new ArrayList<>();
                for (RepositoryEntity entity :
                    repositoryEntities) {
                    repositories.add(entity.toRepository());
                }
                this.repositories.postValue(repositories);
            });
    }
}
