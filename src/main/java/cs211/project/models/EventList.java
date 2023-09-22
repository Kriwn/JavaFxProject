package cs211.project.models;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> events;
    private int lastId = 0;

    public EventList() {
        events = new ArrayList<>();
    }

    public void addNewEvent(String name,String id, String details, String dateStart, String dateEnd, String timeStart, String timeEnd,String countMember, String capacity, Image image){
        name = name.trim();
        details = details.trim();
        dateStart = dateStart.trim();
        dateEnd = dateEnd.trim();
        timeStart = timeStart.trim();
        timeEnd = timeEnd.trim();
        capacity = capacity.trim();
        if (!name.equals("")) {
            Event exist = findEventByName(name);
            if (exist == null) {
                events.add(new Event(name,id, details, dateStart, dateEnd, timeStart, timeEnd,countMember, capacity,image));
                this.lastId = Integer.parseInt(id);
            }
        }
    }

    public void addNewEvent(String name,String id, String details, String dateStart, String dateEnd, String timeStart, String timeEnd,String countMember, String capacity){
        name = name.trim();
        details = details.trim();
        dateStart = dateStart.trim();
        dateEnd = dateEnd.trim();
        timeStart = timeStart.trim();
        timeEnd = timeEnd.trim();
        capacity = capacity.trim();
        if (!name.equals("")) {
            Event exist = findEventByName(name);
            if (exist == null) {
                events.add(new Event(name,id, details, dateStart, dateEnd, timeStart, timeEnd,countMember, capacity));
                this.lastId = Integer.parseInt(id);
            }
        }
    }

    public Event findEventById(int event_id){
        for (Event event : events) {
            if (event.isEventId(event_id)) {
                return event;
            }
        }
        return null;
    }

    public void createEvent(String name, String details, String dateStart, String dateEnd, String timeStart, String timeEnd,String countMember, String capacity, Image image){
        name = name.trim();
        details = details.trim();
        dateStart = dateStart.trim();
        dateEnd = dateEnd.trim();
        timeStart = timeStart.trim();
        timeEnd = timeEnd.trim();
        countMember = countMember.trim();
        capacity = capacity.trim();
        Event exist = findEventByName(name);
        if(!name.equals("")){
            if(exist == null){
                events.add(new Event(name,""+ (++lastId),details,dateStart,dateEnd,timeStart,timeEnd,countMember,capacity,image));
            }
        }
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
