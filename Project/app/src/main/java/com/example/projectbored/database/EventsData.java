package com.example.projectbored.database;

import com.example.projectbored.Location;
import com.example.projectbored.Price;

public class EventsData {
    public static Event[] populateEventsData(){
        return new Event[]{
                new Event( "Fergeteges esemény az Allee-ban",true, Location.Indoors.ordinal(), Price.Paid.ordinal(),
                        "Allee", true),
                new Event( "Fergeteges esemény otthon", false, Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "", false),
                new Event("Közepesen jó esemény az egész családnak", true, Location.Outdoors.ordinal(), Price.Free.ordinal(),
                        "Normafa", true),
                new Event("Rendkívüli esemény egy főre", false, Location.Indoors.ordinal(), Price.Free.ordinal(),
                        "Toilet", true)

        };
    }
}
