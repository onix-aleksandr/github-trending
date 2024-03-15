package com.app.githubtrending.ui.details;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.githubtrending.data.database.AppDatabase;
import com.app.githubtrending.data.database.RepositoriesDao;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.data.entity.database.RepositoryEntity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {

    private RepositoriesDao repositoriesDao;

    public MutableLiveData<Boolean> isInFavourites = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public void switchFavouriteStatus(Repository repository) {
        if (Boolean.TRUE.equals(isInFavourites.getValue())) {
            repositoriesDao.delete(repository.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> setFavouritesStatus(repository));
        } else {
            repositoriesDao.insertAll(RepositoryEntity.toRepositoryEntity(repository))
                .subscribeOn(Schedulers.io())
                .subscribe(() -> setFavouritesStatus(repository));
        }
    }

    public void setupDatabase(AppDatabase database, Repository repository) {
        repositoriesDao = database.userDao();
        setFavouritesStatus(repository);
    }

    @SuppressLint("CheckResult")
    private void setFavouritesStatus(Repository repository) {
        repositoriesDao.isExists(repository.getId())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(exists -> isInFavourites.postValue(exists));
    }
}
