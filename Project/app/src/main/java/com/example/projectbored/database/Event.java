package com.example.projectbored.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    public int eid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "group")
    public boolean group;

    @ColumnInfo(name = "indoor")
    public boolean indoor;

    @ColumnInfo(name = "is_free")
    public boolean is_free;

    @ColumnInfo(name = "search_map")
    public  String search_map;
}
