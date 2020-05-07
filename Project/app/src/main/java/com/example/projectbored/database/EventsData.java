package com.example.projectbored.database;

import com.example.projectbored.Group;
import com.example.projectbored.Location;
import com.example.projectbored.Price;

public class EventsData {
    public static Event[] populateEventsData(){
        return new Event[]{
                new Event( "Watch a movie", Group.Any.ordinal(), Location.Indoors.ordinal(), Price.Any.ordinal(),
                        "", false),
                new Event( "Read a book", Group.Alone.ordinal(), Location.Any.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Go on a hike", Group.Any.ordinal(), Location.Outdoors.ordinal(), Price.Free.ordinal(),
                        "nature preserve", true),
                new Event("Go biking", Group.Alone.ordinal(), Location.Outdoors.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Play board games with family/friends", Group.Group.ordinal(), Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Go bowling", Group.Group.ordinal(), Location.Indoors.ordinal(), Price.Paid.ordinal(),
                        "Bowling", true),
                new Event("Tidy up your home", Group.Alone.ordinal(), Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Go to a theater", Group.Any.ordinal(), Location.Indoors.ordinal(), Price.Paid.ordinal(),
                        "Theater", true),
                new Event("Call a relative and have a conversation", Group.Alone.ordinal(), Location.Any.ordinal(), Price.Paid.ordinal(),
                        "", false),
                new Event("Watch a movie in a cinema", Group.Any.ordinal(), Location.Indoors.ordinal(), Price.Paid.ordinal(),
                        "Cinema", true),
                new Event("Bake a cake", Group.Any.ordinal(), Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Go take a walk", Group.Alone.ordinal(), Location.Outdoors.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Go to a gallery or a museum", Group.Any.ordinal(), Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "Museum", true),
                new Event("Start writing a diary/Continue writing your diary", Group.Alone.ordinal(), Location.Any.ordinal(), Price.Free.ordinal(),
                        "", false),

        };
    }
}
