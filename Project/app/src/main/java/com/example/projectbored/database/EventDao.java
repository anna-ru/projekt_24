package com.example.projectbored.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    public void addEvent(Event event);

    @Query("SELECT * FROM events WHERE id=:id")
    public Event getEvent(int id);

    @Query("SELECT * FROM events")
    public List<Event> getEvents();

    @Delete
    public void deleteEvent(Event event);

    @Update
    public void updateEvent(Event event);

    @Insert
    public void populateDatabase(Event[] events);

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Event... events);

    @Insert
    public void insertAll(Event[] events);

    @Update
    public void updateEvents(Event... events);

    @Delete
    public void deleteEvents(Event... event);
}