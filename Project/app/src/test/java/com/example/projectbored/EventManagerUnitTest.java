package com.example.projectbored;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class EventManagerUnitTest {

    private EventManager eventManager;

    @Before
    public void SetUp() {
        eventManager = new EventManager();
    }

    @Test
    public void fillEventsListWithSampleDataTest() {
        eventManager.fillEventsListWithSampleData();
        int length = eventManager.getEventsList().size();
        assertNotEquals("List of events was 0", 0, length);
    }

    @Test
    public void getRandomElementOfEventsListByParametersTestIsGroup() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, TimeOfDay.Morning, Price.Cheap));
        eventManager.addEventToList(new EventClass("test2","",false, false, TimeOfDay.Afternoon, Price.Expensive));

        eventManager.setSelectedTimeOfDay(TimeOfDay.Any);
        eventManager.setSelectedPrice(Price.Any);
        eventManager.setSearchForGroup(true);

        EventClass random = eventManager.getRandomElementOfEventsListByParameters();
        assertEquals("isGroup property not checked properly",true, random.getGroup());
    }

    @Test
    public void getRandomElementOfEventsListByParametersTestTimeOfDay() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, TimeOfDay.Morning, Price.Cheap));
        eventManager.addEventToList(new EventClass("test2","",false, false, TimeOfDay.Afternoon, Price.Expensive));

        eventManager.setSelectedTimeOfDay(TimeOfDay.Morning);
        eventManager.setSelectedPrice(Price.Any);
        eventManager.setSearchForGroup(false);

        EventClass random = eventManager.getRandomElementOfEventsListByParameters();
        assertEquals("TimeOfDay property not checked properly",TimeOfDay.Morning, random.getTimeOfDay());
    }

    @Test
    public void getRandomElementOfEventsListByParametersTestPrice() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, TimeOfDay.Morning, Price.Cheap));
        eventManager.addEventToList(new EventClass("test2","",false, false, TimeOfDay.Afternoon, Price.Expensive));

        eventManager.setSelectedTimeOfDay(TimeOfDay.Any);
        eventManager.setSelectedPrice(Price.Expensive);
        eventManager.setSearchForGroup(false);

        EventClass random = eventManager.getRandomElementOfEventsListByParameters();
        assertEquals("Price property not checked properly",Price.Expensive, random.getPrice());
    }

    @Test
    public void FilterTimeOfDayTestAny() {
        eventManager.fillEventsListWithSampleData();
        eventManager.setSelectedTimeOfDay(TimeOfDay.Any);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterTimeOfDay(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertEquals("TimeOfDay filtering when set to any removed items from list",lengthBefore, lengthAfter);
    }

    @Test
    public void FilterTimeOfDayTestNormal() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, TimeOfDay.Morning, Price.Cheap));
        eventManager.addEventToList(new EventClass("test2","",false, false, TimeOfDay.Afternoon, Price.Expensive));
        eventManager.setSelectedTimeOfDay(TimeOfDay.Morning);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterTimeOfDay(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertNotEquals("TimeOfDay filtering didn't remove item from list",lengthBefore, lengthAfter);
    }

    @Test
    public void FilterPriceTestAny() {
        eventManager.fillEventsListWithSampleData();
        eventManager.setSelectedPrice(Price.Any);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterTimeOfDay(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertEquals("Price filtering when set to any removed items from list",lengthBefore, lengthAfter);
    }

    @Test
    public void FilterPriceTestNormal() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, TimeOfDay.Morning, Price.Cheap));
        eventManager.addEventToList(new EventClass("test2","",false, false, TimeOfDay.Afternoon, Price.Expensive));
        eventManager.setSelectedPrice(Price.Cheap);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterPrice(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertNotEquals("Price filtering didn't remove item from list",lengthBefore, lengthAfter);
    }

}
