package com.example.projectbored.database;

import android.app.Application;
import android.os.AsyncTask;

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
        /*AppDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.insert(event);
        });*/
        new InsertEventAsyncTask(eventDao).execute(event);
    }

    public void update(Event event){
        new UpdateEventAsyncTask(eventDao).execute(event);
    }

    public void delete(Event event){
        new DeleteEventAsyncTask(eventDao).execute(event);
    }

    private static class InsertEventAsyncTask extends AsyncTask<Event, Void, Void>{
        private EventDao eventDao;

        private InsertEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insert(events[0]);
            return null;
        }
    }

    private static class UpdateEventAsyncTask extends AsyncTask<Event, Void, Void>{
        private EventDao eventDao;

        private UpdateEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.updateEvents(events[0]);
            return null;
        }
    }

    private static class DeleteEventAsyncTask extends AsyncTask<Event, Void, Void>{
        private EventDao eventDao;

        private DeleteEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.deleteEvents(events[0]);
            return null;
        }
    }
}
