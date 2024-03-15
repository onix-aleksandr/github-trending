package com.app.githubtrending.data.database;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseHolder {

    private static AppDatabase database;

    public static synchronized AppDatabase getDatabaseInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                .build();
        }
        return database;
    }
}
