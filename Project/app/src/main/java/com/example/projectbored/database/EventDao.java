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
