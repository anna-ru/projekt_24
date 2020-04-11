package com.example.projectbored.database;

public class EventsData {
    public static Event[] populateEventsData(){
        return new Event[]{
                new Event(1, "Fergeteges esemény az Allee-ban", true, true, false,
                        "Allee", true),
                new Event(2, "Fergeteges esemény otthon", false, true, true,
                        "", false),
                new Event(3, "Közepesen jó esemény az egész családnak", true, false, true,
                        "Normafa", true),
                new Event(3, "Rendkívüli esemény egy főre", false, true, true,
                        "Toilet", true)

        };
    }
}
