package cs211.project.models;

import java.util.ArrayList;

public class Event {
    private String name;
    private String detail;
    private String dateStart;
    private String dateEnd;
    private String timeStart;
    private String timeEnd;
    private int capacity;
    private ArrayList<Team> team;
    private ArrayList<NormalUser> user;

    public Event(String name, String details, String dateStart, String dateEnd, String timeStart, String timeEnd, int capacity){
        this.name = name;
        this.detail = details;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.capacity = capacity;
    }
    public void addTeam(Team team){

    }

    public boolean isName(String name) {
        return this.name.equals(name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Team> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Team> team) {
        this.team = team;
    }

    public ArrayList<NormalUser> getUser() {
        return user;
    }

    public void setUser(ArrayList<NormalUser> user) {
        this.user = user;
    }
}
