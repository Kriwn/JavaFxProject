package cs211.project.models;

import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> events;
    private ArrayList<Team> teams;

    public EventList() {
        events = new ArrayList<>();
        teams = new ArrayList<>();
    }

    public void addNewEvent(String name, String details, String dateStart, String dateEnd, String timeStart, String timeEnd, int capacity){
        name = name.trim();
        details = details.trim();
        dateStart = dateStart.trim();
        dateEnd = dateEnd.trim();
        timeStart = timeStart.trim();
        timeEnd = timeEnd.trim();
        if (!name.equals("") && !details.equals("") && !dateStart.equals("") && !dateEnd.equals("") && !timeStart.equals("") && !timeEnd.equals("")) {
            Event exist = findEventByName(name);
            if (exist == null) {
                events.add(new Event(name, details, dateStart, dateEnd, timeStart, timeEnd, capacity));
            }
        }
    }

    public void addTeam(){
        
    }

    public Event findEventByName(String name) {
        for (Event event : events) {
            if (event.isName(name)) {
                return event;
            }
        }
        return null;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
