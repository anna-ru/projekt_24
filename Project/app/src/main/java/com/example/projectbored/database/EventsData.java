package com.example.projectbored.database;

import com.example.projectbored.Group;
import com.example.projectbored.Location;
import com.example.projectbored.Price;

public class EventsData {
    public static Event[] populateEventsData(){
        return new Event[]{
                new Event( "Fergeteges esemény az Allee-ban", Group.Group.ordinal(), Location.Indoors.ordinal(), Price.Paid.ordinal(),
                        "Allee", true),
                new Event( "Fergeteges esemény otthon", Group.Any.ordinal(), Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Közepesen jó esemény az egész családnak", Group.Group.ordinal(), Location.Outdoors.ordinal(), Price.Free.ordinal(),
                        "Normafa", true),
                new Event("Rendkívüli esemény egy főre", Group.Alone.ordinal(), Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "Toilet", true)

        };
    }
}
