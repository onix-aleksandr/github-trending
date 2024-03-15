package com.app.githubtrending.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.app.githubtrending.data.entity.database.RepositoryEntity;

@Database(entities = {RepositoryEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepositoriesDao userDao();
}
