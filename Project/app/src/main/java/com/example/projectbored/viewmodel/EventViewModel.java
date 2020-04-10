package com.example.projectbored.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projectbored.database.Event;
import com.example.projectbored.database.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private EventRepository eventRepository;
    private LiveData<List<Event>> mEventList;

    public EventViewModel(Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        mEventList = eventRepository.getEventList();
    }

    public LiveData<List<Event>> getAllEvents() {
        return mEventList;
    }

    public void insert(Event event) { eventRepository.insert(event); }
}
