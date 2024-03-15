package com.app.githubtrending.data.database;

import static com.app.githubtrending.data.entity.database.RepositoryEntity.ID;
import static com.app.githubtrending.data.entity.database.RepositoryEntity.STARS_COUNT;
import static com.app.githubtrending.data.entity.database.RepositoryEntity.TABLE_NAME;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.app.githubtrending.data.entity.database.RepositoryEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface RepositoriesDao {

    @Query(value = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + STARS_COUNT + " DESC")
    Single<List<RepositoryEntity>> getFavourites();

    @Upsert
    Completable insertAll(RepositoryEntity... entity);

    @Query("DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = :id")
    Completable delete(long id);

    @Query("SELECT EXISTS (SELECT 1 FROM " + TABLE_NAME + " WHERE id = :id LIMIT 1)")
    Single<Boolean> isExists(long id);
}
