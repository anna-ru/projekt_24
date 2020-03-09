package com.example.projectbored.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Event {
    @PrimaryKey
    public int eid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "group")
    public boolean group;

    @ColumnInfo(name = "indoor")
    public boolean indoor;

    @ColumnInfo(name = "morning")
    public  boolean morning;

    @ColumnInfo(name = "afternoon")
    public  boolean afternoon;

    @ColumnInfo(name = "evening")
    public  boolean evening;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "categories")
    public String categories;
}
