package com.example.projectbored;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class EventManager {

    private boolean searchForGroup;
    private TimeOfDay selectedTimeOfDay = TimeOfDay.Any;
    private Price selectedPrice = Price.Any;
    private List<EventClass> eventsList = new LinkedList<EventClass>();

    public void fillEventsListWithSampleData(){
        clearEventsList();
        eventsList.add(new EventClass("Fergeteges esemény az Alle-ban","Alle",true, true,TimeOfDay.Morning,Price.Cheap));
        eventsList.add(new EventClass("Fergeteges esemény otthon","",false,false,TimeOfDay.Morning,Price.Free));
        eventsList.add(new EventClass("Közepesen jó esemény az egész családnak","Budapest",true,true,TimeOfDay.Evening,Price.Mediocre));
        eventsList.add(new EventClass("Rendkívüli esemény egy főre","Toilet",true,false,TimeOfDay.Noon,Price.Cheap));
    }

    public EventClass getRandomElementOfEventsListByParameters(){
        EventClass result;
        Random rand = new Random();
        List<EventClass> randomEventPool = new LinkedList<EventClass>(eventsList);

        if(searchForGroup) {
            for (int i = 0; i < eventsList.size(); i++) {
                if (!eventsList.get(i).getGroup()) {
                    randomEventPool.remove(eventsList.get(i));
                }
            }
        }

        FilterTimeOfDay(randomEventPool);
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

    public void FilterTimeOfDay(List<EventClass> randomEventPool){
        if(selectedTimeOfDay.equals(TimeOfDay.Any)) return;
        for (int i = 0; i < eventsList.size(); i++) {
            if (!(eventsList.get(i).getTimeOfDay().equals(selectedTimeOfDay))) {
                randomEventPool.remove(eventsList.get(i));
            }
        }
    }

    public void FilterPrice(List<EventClass> randomEventPool){
        if(selectedPrice.equals(Price.Any)) return;
        for (int i = 0; i < eventsList.size(); i++) {
            if (!(eventsList.get(i).getPrice().equals(selectedPrice))) {
                randomEventPool.remove(eventsList.get(i));
            }
        }
    }

    public TimeOfDay StringToTimeOfDay(String selected) {
        if(selected.equals("Anytime")) return TimeOfDay.Any; //if we change the TimeOfDay enums we need to be careful to see it this still works
        return TimeOfDay.valueOf(selected);
    }

    public Price StringToPrice(String selected) {
        if(selected.equals("Any price")) return Price.Any; //if we change the Price enums we need to be careful to see it this still works
        return Price.valueOf(selected);
    }

    //getters
    public boolean getSearchForGroup() {return searchForGroup;}
    public TimeOfDay getSelectedTimeOfDay() {return selectedTimeOfDay;}
    public Price getSelectedPrice() {return selectedPrice;}
    public List<EventClass> getEventsList() {return eventsList;}
    //setters
    public void setSearchForGroup(boolean selected) {searchForGroup = selected;}
    public void setSelectedTimeOfDay(TimeOfDay selected) {selectedTimeOfDay = selected;}
    public void setSelectedPrice(Price selected) {selectedPrice = selected;}
    //other
    public void addEventToList(EventClass event) {eventsList.add(event);}
    public void clearEventsList() {eventsList.clear();}

}
