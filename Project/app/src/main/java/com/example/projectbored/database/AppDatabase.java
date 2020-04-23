package com.example.projectbored.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EventDao eventDao();
    private static AppDatabase INSTANCE;

    @VisibleForTesting
    public static final String DATABASE_NAME = "events";

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDao eventDao;

        private PopulateDbAsyncTask(AppDatabase db){
            this.eventDao = db.eventDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eventDao.insert(new Event("Fergeteges esemény az Allee-ban", true, true, false,
                    "Allee", true));
            eventDao.insert(new Event( "Fergeteges esemény otthon", false, true, true,
                    "", false));
            eventDao.insert(new Event("Közepesen jó esemény az egész családnak", true, false, true,
                    "Normafa", true));
            eventDao.insert(new Event( "Rendkívüli esemény egy főre", false, true, true,
                    "Toilet", true));
            return null;
        }
    }

    public static synchronized AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

}
