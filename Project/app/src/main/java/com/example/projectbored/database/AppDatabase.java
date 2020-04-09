package com.example.projectbored.database;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
//    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "events-db";

}
