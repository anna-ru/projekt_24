package com.example.projectbored;

import android.util.Log;

import com.example.projectbored.database.Event;

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
        eventManager.addEventToList(new Event("test1",true,1, 1, null, false));
        eventManager.addEventToList(new Event("test2",false,0, 0, null, false));

        eventManager.setSelectedLocation(Location.Any);
        eventManager.setSelectedPrice(Price.Any);
        eventManager.setSearchForGroup(true);

        Event random = eventManager.getRandomElementOfEventsListByParameters();
        assertEquals("isGroup property not checked properly",true, random.isIs_group());
    }

    // TODO: we could use fail() method here instead of this check

    @Test
    public void getRandomElementOfEventsListByParametersTestLocationIndoors() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new Event("test1",false,Location.Indoors.ordinal(), Price.Free.ordinal(), null, false));
        eventManager.addEventToList(new Event("test2",false,Location.Any.ordinal(), Price.Any.ordinal(), null, false));

        eventManager.setSelectedLocation(Location.Indoors);
        eventManager.setSelectedPrice(Price.Any);
        eventManager.setSearchForGroup(false);

        Event random = eventManager.getRandomElementOfEventsListByParameters();

        if(random.getIs_indoor() != Location.Any.ordinal() && random.getIs_indoor() != Location.Indoors.ordinal()){
            fail("Expected: Indoors or Any, but actual was something else");
        }
    }

    @Test
    public void getRandomElementOfEventsListByParametersTestPrice() {
        eventManager.clearEventsList();
        eventManager.addEventToList(new Event("test1",true,Location.Any.ordinal(), Price.Any.ordinal(), null, false));
        eventManager.addEventToList(new Event("test2",false,Location.Any.ordinal(), Price.Free.ordinal(), null, false));

        eventManager.setSelectedLocation(Location.Any);
        eventManager.setSelectedPrice(Price.Free);
        eventManager.setSearchForGroup(false);

        Event random = eventManager.getRandomElementOfEventsListByParameters();

        if(random.getIs_free() != Price.Any.ordinal() && random.getIs_free() != Price.Free.ordinal()){
            fail("Expected: Free or Any, but actual was something else");
        }
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
        eventManager.addEventToList(new Event("test1",true,Location.Indoors.ordinal(), Price.Any.ordinal(), null, false));
        eventManager.addEventToList(new Event("test2",false,Location.Outdoors.ordinal(), Price.Any.ordinal(), null, false));
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
        eventManager.addEventToList(new Event("test1",true,Location.Indoors.ordinal(), Price.Free.ordinal(), null, false));
        eventManager.addEventToList(new Event("test2",false,Location.Outdoors.ordinal(), Price.Paid.ordinal(), null, false));
        eventManager.setSelectedPrice(Price.Free);

        int lengthBefore = eventManager.getEventsList().size();
        eventManager.FilterPrice(eventManager.getEventsList());
        int lengthAfter = eventManager.getEventsList().size();

        assertNotEquals("Price filtering didn't remove item from list",lengthBefore, lengthAfter);
    }

}
