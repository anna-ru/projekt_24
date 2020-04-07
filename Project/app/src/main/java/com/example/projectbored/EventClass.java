package com.example.projectbored;

import java.util.LinkedList;
import java.util.List;

public class EventClass {

    private String name;
    private String mapsData;
    private boolean showMap;
    private boolean isGroup;
    private TimeOfDay timeOfDay;
    private Price price;
    private List<Categories> categories;

    public EventClass(String name, String mapsData, boolean showMap, boolean isGroup, TimeOfDay timeOfDay,Price price){
        this.name = name;
        this.mapsData = mapsData;
        this.showMap = showMap;
        this.isGroup = isGroup;
        this.timeOfDay = timeOfDay;
        this.price = price;
        this.categories = new LinkedList<Categories>();
    }
//getters

    public String getName() { return name; }
    public String getMapsData() { return mapsData; }
    public boolean getShowMap() { return showMap; }
    public boolean getGroup() { return isGroup; }
    public TimeOfDay getTimeOfDay() { return timeOfDay; }
    public Price getPrice() { return price; }
    public List<Categories> getCategories() { return categories; }

//setters
    public void setName(String name) { this.name = name; }
    public void setMapsData(String mapsData) { this.mapsData = mapsData; }
    public void setShowMap(boolean showMap) { this.showMap = showMap; }
    public void setGroup(boolean group) { isGroup = group; }
    public void setTimeOfDay(TimeOfDay timeOfDay) { this.timeOfDay = timeOfDay; }
    public void setPrice(Price price) { this.price = price; }
    public void setCategories(List<Categories> categories) { this.categories = categories; }

}
