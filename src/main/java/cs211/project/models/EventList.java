package cs211.project.models;

import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> events;

    public EventList() {
        events = new ArrayList<>();
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

    public void addTeam(Team team,String name){
        Event exist = findEventByName(name);
        exist.addTeam(team);
    }

    public void addUser(User user, String name){
        Event exist = findEventByName(name);
        exist.addUser(user);
    }

    public void editEvent(String name,String name_new, String details, String dateStart, String timeStart, String dateEnd, String timeEnd, int capacity){
        Event exist = findEventByName(name);
        exist.setName(name_new);
        exist.setDetail(details);
        exist.setDateStart(dateStart);
        exist.setTimeStart(timeStart);
        exist.setDateEnd(dateEnd);
        exist.setTimeEnd(timeEnd);
        exist.setCapacity(capacity);
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
