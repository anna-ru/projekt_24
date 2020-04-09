package com.example.projectbored.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Event> events);
}
