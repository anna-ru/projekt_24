
package com.example.projectbored.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EventRepository {

    private LiveData<List<Event>> eventList;
    private EventDao eventDao;

    public EventRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        eventDao = db.eventDao();
        eventList = eventDao.getAll();
    }

    public LiveData<List<Event>> getEventList() {
        return eventList;
    }

    public void insert(Event event) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.insert(event);
        });
    }
}