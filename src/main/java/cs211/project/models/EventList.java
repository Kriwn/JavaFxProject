package cs211.project.models;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> events;

    public EventList() {
        events = new ArrayList<>();
    }

    public void addNewEvent(String name, String details, String dateStart, String dateEnd, String timeStart, String timeEnd,String countMember, String capacity, Image image){
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
                events.add(new Event(name, details, dateStart, dateEnd, timeStart, timeEnd,countMember, capacity,image));
            }
        }
    }

    public Event findEventByPosition(int position){
        for (Event event : events) {
            if (event.isPosition(position)) {
                return event;
            }
        }
        return null;
    }

    public void addTeam(Team team,String name){
        Event exist = findEventByName(name);
        exist.addTeam(team);
    }

    public void addCountEvent(String name){
        Event exist = findEventByName(name);
        exist.addCountEvent();
    }

    public void joinEvent(User user, String name){
        Event exist = findEventByName(name);
        if (exist.getMaxMember() > exist.getCountMember() && exist.getStatus() == 1){
            exist.addUser(user);
            exist.addCountMember();
        }
    }

    public void checkTimeEvent(String name,LocalDate date, LocalTime time){
        Event exist = findEventByName(name);
        if(exist.getDateStart().isAfter(date) && exist.getDateEnd().isBefore(date) && exist.getTimeStart().isAfter(time) && exist.getTimeEnd().isBefore(time)){
            exist.setStatus(1);
        }
        else{
            exist.setStatus(0);
        }
    }


    public void editEvent(String name,String name_new, String details, String dateStart, String timeStart, String dateEnd, String timeEnd, int capacity, Image image){
        Event exist = findEventByName(name);
        exist.setName(name_new);
        exist.setDetail(details);
        exist.setDateStart(dateStart);
        exist.setTimeStart(timeStart);
        exist.setDateEnd(dateEnd);
        exist.setTimeEnd(timeEnd);
        exist.setCapacity(capacity);
        exist.setImage(image);
    }

    public void banUser(User user, String name){
        Event exist = findEventByName(name);
        exist.removeUser(user);
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
