package com.example.projectbored.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;
    private boolean is_group;
    private boolean is_indoor;
    private boolean is_free;
    private  String search_map;
    private boolean show_map;

    public Event(int id, String name, boolean is_group, boolean is_indoor, boolean is_free,
                 String search_map, boolean show_map) {
        this.id = id;
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

    public boolean isIs_indoor() {
        return is_indoor;
    }

    public void setIs_indoor(boolean is_indoor) {
        this.is_indoor = is_indoor;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
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
