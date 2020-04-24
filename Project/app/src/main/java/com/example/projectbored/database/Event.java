package com.example.projectbored.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "eventName")
    private String name;

    @ColumnInfo(name = "eventIsGroup")
    private boolean is_group;

    @ColumnInfo(name = "eventIsIndoor")
    private int is_indoor;

    @ColumnInfo(name = "eventIsFree")
    private int is_free;

    @ColumnInfo(name = "eventSearchMap")
    private  String search_map;

    @ColumnInfo(name = "eventShowMap")
    private boolean show_map;

    public Event(String name, boolean is_group, int is_indoor, int is_free,
                 String search_map, boolean show_map) {
        //this.id = id;
        this.name = name;
        this.is_group = is_group;
        this.is_free = is_free;
        this.is_indoor = is_indoor;
        this.search_map = search_map;
        this.show_map = show_map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_group() {
        return is_group;
    }

    public void setIs_group(boolean is_group) {
        this.is_group = is_group;
    }

    public int getIs_indoor() {
        return is_indoor;
    }

    public int getIs_free() {
        return is_free;
    }

    public int isIs_indoor() {
        return is_indoor;
    }

    public void setIs_indoor(int is_indoor) {
        this.is_indoor = is_indoor;
    }

    public int isIs_free() {
        return is_free;
    }

    public void setIs_free(int is_free) {
        this.is_free = is_free;
    }

    public String getSearch_map() {
        return search_map;
    }

    public void setSearch_map(String search_map) {
        this.search_map = search_map;
    }

    public boolean isShow_map() {
        return show_map;
    }

    public void setShow_map(boolean show_map) {
        this.show_map = show_map;
    }
}