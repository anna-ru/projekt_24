package com.example.projectbored;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * This class handles getting a random event, filtering events and other things related to the
 * functionality of the idea button, and the options menu.
 *
 * @author  Szabó Bence
 * @version 1.1
 * @since   2020-04-07
 */
public class EventManager {

    private boolean searchForGroup;
    private Location selectedLocation = Location.Any;
    private Price selectedPrice = Price.Any;
    private LinkedList<EventClass> eventsList = new LinkedList<EventClass>();

    /**
     * Fills the eventsList with sample data (temporary function until database gets setup)
     */
    public void fillEventsListWithSampleData(){
        ArrayList<String> titleList = new ArrayList<String>();
        for(int i=0;i<eventsList.size();i++){
            titleList.add(eventsList.get(i).getName());
        }
        EventClass newEvent = new EventClass("Fergeteges esemény az Alle-ban","Alle",true, true, Location.Indoors,Price.Paid);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
        newEvent = new EventClass("Fergeteges esemény otthon","",false,false, Location.Indoors,Price.Free);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
        newEvent = new EventClass("Közepesen jó esemény az egész családnak","Budapest",true,true, Location.Outdoors,Price.Paid);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
        newEvent = new EventClass("Rendkívüli esemény egy főre","Toilet",true,false, Location.Indoors,Price.Free);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
    }

    /**
     * Gets a random event from the list of events. If any filters are set
     * (price, location, isGroup) then it chooses accordingly
     *
     * @return EventClass The random event that didn't get filtered out
     */
    public EventClass getRandomElementOfEventsListByParameters(){
        EventClass result;
        Random rand = new Random();
        LinkedList<EventClass> randomEventPool = new LinkedList<EventClass>(eventsList);

        if(searchForGroup) {
            for (int i = 0; i < eventsList.size(); i++) {
                if (!eventsList.get(i).getGroup()) {
                    randomEventPool.remove(eventsList.get(i));
                }
            }
        }

        FilterLocation(randomEventPool);
        FilterPrice(randomEventPool);

        // Generate random integers in range 0 to size of List
        if(randomEventPool.size() > 0) {
            int randomNumber = rand.nextInt(randomEventPool.size());
            result = randomEventPool.get(randomNumber);
            return result;
        }else{
            result = new EventClass("No idea found.","",false,false,null,null);
            return result;
        }
    }

    /**
     * Filters the given list of events according to what was set in the options menu
     * relating to the location
     *
     * @param randomEventPool This list of events will be filtered
     */
    public void FilterLocation(List<EventClass> randomEventPool){
        if(selectedLocation.equals(Location.Any)) return;
        for (int i = 0; i < eventsList.size(); i++) {
            if (!(eventsList.get(i).getLocation().equals(selectedLocation))) {
                randomEventPool.remove(eventsList.get(i));
            }
        }
    }

    /**
     * Filters the given list of events according to what was set in the options menu
     * relating to the price
     *
     * @param randomEventPool This list of events will be filtered
     */
    public void FilterPrice(List<EventClass> randomEventPool){
        if(selectedPrice.equals(Price.Any)) return;
        for (int i = 0; i < eventsList.size(); i++) {
            if (!(eventsList.get(i).getPrice().equals(selectedPrice))) {
                randomEventPool.remove(eventsList.get(i));
            }
        }
    }

    /**
     * Converts a string to a location
     *
     * @param selected This is the string that was chosen from the spinner
     * @return Location returns the valueOf conversion of the string,
     * except in the case of "Anywhere", when it returns Location.Any
     */
    public Location StringToLocation(String selected) {
        if(selected.equals("Anywhere")) return Location.Any; //if we change the Location enums we need to be careful to see it this still works
        return Location.valueOf(selected);
    }

    /**
     * Converts a string to a Price
     *
     * @param selected This is the string that was chosen from the spinner
     * @return Price returns the valueOf conversion of the string,
     * except in the case of "Any price", when it returns Price.Any
     */
    public Price StringToPrice(String selected) {
        if(selected.equals("Any price")) return Price.Any; //if we change the Price enums we need to be careful to see it this still works
        return Price.valueOf(selected);
    }

    //getters
    public boolean getSearchForGroup() {return searchForGroup;}
    public Location getSelectedLocation() {return selectedLocation;}
    public Price getSelectedPrice() {return selectedPrice;}
    public List<EventClass> getEventsList() {return eventsList;}
    //setters
    public void setSearchForGroup(boolean selected) {searchForGroup = selected;}
    public void setSelectedLocation(Location selected) {
        selectedLocation = selected;}
    public void setSelectedPrice(Price selected) {selectedPrice = selected;}
    //other
    /**
     * Adds an event to the list of events
     *
     * @param event This is the event that will be added to the list
     */
    public void addEventToList(EventClass event) {eventsList.add(event);}

    /**
     * Clears the list of events
     */
    public void clearEventsList() {eventsList.clear();}

}
