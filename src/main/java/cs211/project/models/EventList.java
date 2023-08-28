package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> events;

    public EventList() {
        events = new ArrayList<>();
    }

    public void addNewEvent(String name, String details, String dateStart, String dateEnd, String timeStart,String timeEnd,String capacity){
        name = name.trim();
        details = details.trim();
        dateStart = dateStart.trim();
        dateEnd = dateEnd.trim();
        timeStart = timeStart.trim();
        timeEnd = timeEnd.trim();
        capacity = capacity.trim();
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

    public void joinEvent(NormalUser user, String name){
        Event exist = findEventByName(name);
        if (exist.getMaxMember() > exist.getCountMember() && exist.getStates() == 1){
            exist.addUser(user);
            exist.addCountMember();
        }
    }

    public void checkTimeEvent(String name,LocalDate date, LocalTime time){
        Event exist = findEventByName(name);
        if(exist.getDateStart().isAfter(date) && exist.getDateEnd().isBefore(date) && exist.getTimeStart().isAfter(time) && exist.getTimeEnd().isBefore(time)){
            exist.setStates(1);
        }
        else{
            exist.setStates(0);
        }
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

    public void banUser(NormalUser user, String name){
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
