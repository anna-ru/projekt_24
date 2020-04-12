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
        eventManager.addEventToList(new EventClass("test1","",false, true, Location.Indoors, Price.Free));
        eventManager.addEventToList(new EventClass("test2","",false, false, Location.Outdoors, Price.Paid));

        eventManager.setSelectedLocation(Location.Any);
        eventManager.setSelectedPrice(Price.Any);
        eventManager.setSearchForGroup(true);

        EventClass random = eventManager.getRandomElementOfEventsListByParameters();
        assertEquals("isGroup property not checked properly",true, random.getGroup());
    }

    @Test
    public void getRandomElementOfEventsListByParametersTestLocation() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, Location.Indoors, Price.Free));
        eventManager.addEventToList(new EventClass("test2","",false, false, Location.Outdoors, Price.Paid));

        eventManager.setSelectedLocation(Location.Indoors);
        eventManager.setSelectedPrice(Price.Any);
        eventManager.setSearchForGroup(false);

        EventClass random = eventManager.getRandomElementOfEventsListByParameters();
        assertEquals("Location property not checked properly",Location.Indoors, random.getLocation());
    }

    @Test
    public void getRandomElementOfEventsListByParametersTestPrice() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, Location.Indoors, Price.Free));
        eventManager.addEventToList(new EventClass("test2","",false, false, Location.Outdoors, Price.Paid));

        eventManager.setSelectedLocation(Location.Any);
        eventManager.setSelectedPrice(Price.Paid);
        eventManager.setSearchForGroup(false);

        EventClass random = eventManager.getRandomElementOfEventsListByParameters();
        assertEquals("Price property not checked properly",Price.Paid, random.getPrice());
    }

    @Test
    public void FilterLocationTestAny() {
        eventManager.fillEventsListWithSampleData();
        eventManager.setSelectedLocation(Location.Any);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterLocation(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertEquals("Location filtering when set to any removed items from list",lengthBefore, lengthAfter);
    }

    @Test
    public void FilterLocationTestNormal() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, Location.Indoors, Price.Free));
        eventManager.addEventToList(new EventClass("test2","",false, false, Location.Outdoors, Price.Paid));
        eventManager.setSelectedLocation(Location.Indoors);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterLocation(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertNotEquals("Location filtering didn't remove item from list",lengthBefore, lengthAfter);
    }

    @Test
    public void FilterPriceTestAny() {
        eventManager.fillEventsListWithSampleData();
        eventManager.setSelectedPrice(Price.Any);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterLocation(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertEquals("Price filtering when set to any removed items from list",lengthBefore, lengthAfter);
    }

    @Test
    public void FilterPriceTestNormal() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new EventClass("test1","",false, true, Location.Indoors, Price.Free));
        eventManager.addEventToList(new EventClass("test2","",false, false, Location.Outdoors, Price.Paid));
        eventManager.setSelectedPrice(Price.Free);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterPrice(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertNotEquals("Price filtering didn't remove item from list",lengthBefore, lengthAfter);
    }

}
