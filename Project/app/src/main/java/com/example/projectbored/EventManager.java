package com.example.projectbored;

import android.util.Log;

import com.example.projectbored.database.Event;

import java.lang.reflect.Array;
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

// TODO: Change everything to work with Event instead of EventClass
public class EventManager {

    private boolean searchForGroup;
    private Location selectedLocation = Location.Any;
    private Price selectedPrice = Price.Any;
    //private LinkedList<EventClass> eventsList = new LinkedList<EventClass>();
    private ArrayList<Event> eventsList = new ArrayList<Event>();

    /**
     * Fills the eventsList with sample data (temporary function until database gets setup)
     */
//TODO: this is now just for unitTests.
    public void fillEventsListWithSampleData(){
        ArrayList<String> titleList = new ArrayList<String>();
        for(int i=0;i<eventsList.size();i++){
            titleList.add(eventsList.get(i).getName());
        }
        Event newEvent = new Event("Fergeteges esemény az Alle-ban",true,Location.Indoors.ordinal(), Price.Free.ordinal(), "Alle",true);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
        newEvent = new Event("Fergeteges esemény otthon",false,Location.Indoors.ordinal(),Price.Free.ordinal(), "",false);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
        newEvent = new Event("Közepesen jó esemény az egész családnak",true,Location.Outdoors.ordinal(),Price.Paid.ordinal(), "Budapest",true);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
        newEvent = new Event("Rendkívüli esemény egy főre",false,Location.Indoors.ordinal(),Price.Free.ordinal(), "Toilet",true);
        if(!titleList.contains(newEvent.getName())){
            eventsList.add(newEvent);
        }
    }

    public void getDataFromDatabase(ArrayList<Event> dbEventList){
        eventsList.clear();
        eventsList.addAll(dbEventList);
    }

    /**
     * Gets a random event from the list of events. If any filters are set
     * (price, location, isGroup) then it chooses accordingly
     *
     * @return EventClass The random event that didn't get filtered out
     */
    public Event getRandomElementOfEventsListByParameters(){
        Event result;
        Random rand = new Random();
        //LinkedList<EventClass> randomEventPool = new LinkedList<EventClass>(eventsList);
        LinkedList<Event> randomEventPool = new LinkedList<Event>(eventsList);

        if(searchForGroup) {
            for (int i = 0; i < eventsList.size(); i++) {
                if (!eventsList.get(i).isIs_group()) {
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

            //return new Event(result.getName(),result.getGroup(),result.getLocation().ordinal(),result.getPrice().ordinal(),result.getMapsData(),result.getShowMap());
            return result;
        }else{
            result = new Event("No idea found.",false,0,0,null,false);
            //return new Event(result.getName(),result.getGroup(),result.getLocation().ordinal(),result.getPrice().ordinal(),result.getMapsData(),result.getShowMap());
            return result;
        }
    }

    /**
     * Filters the given list of events according to what was set in the options menu
     * relating to the location
     *
     * @param randomEventPool This list of events will be filtered
     */
    public void FilterLocation(List<Event> randomEventPool){
        if(selectedLocation.equals(Location.Any)) return;
        for (int i = 0; i < eventsList.size(); i++) {

            if (!(eventsList.get(i).getIs_indoor() == selectedLocation.ordinal())) {
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
    public void FilterPrice(List<Event> randomEventPool){
        if(selectedPrice.equals(Price.Any)) return;
        for (int i = 0; i < eventsList.size(); i++) {
            if (!(eventsList.get(i).getIs_free() == selectedPrice.ordinal())) {
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
    public List<Event> getEventsList() {return eventsList;}
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
    public void addEventToList(Event event) {eventsList.add(event);}

    /**
     * Clears the list of events
     */
    public void clearEventsList() {eventsList.clear();}

}
