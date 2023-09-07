package cs211.project.models;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    public static int gen_id = 1;
    private int event_id;
    private String name;
    private String detail;
    private  int countMember;
    private int maxMember;
    private Image image;
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private int status;

    public Event(String name, String details,String dateStart,String dateEnd,String timeStart,String timeEnd,String countMember, String maxMember,Image image){
        this.name = name;
        this.detail = details;
        this.maxMember = Integer.parseInt(maxMember);
        this.dateStart = LocalDate.parse(dateStart);
        this.dateEnd = LocalDate.parse(dateEnd);
        this.timeStart = LocalTime.parse(timeStart);
        this.timeEnd = LocalTime.parse(timeEnd);
        this.countMember = Integer.parseInt(countMember);
        this.status = 0;
        this.image = image;
        this.event_id = gen_id;
    }

    public Event(String name, String details,String dateStart,String dateEnd,String timeStart,String timeEnd, String maxMember){
        this.name = name;
        this.detail = details;
        this.maxMember = Integer.parseInt(maxMember);
        this.dateStart = LocalDate.parse(dateStart);
        this.dateEnd = LocalDate.parse(dateEnd);
        this.timeStart = LocalTime.parse(timeStart);
        this.timeEnd = LocalTime.parse(timeEnd);
        this.countMember = 0;
        this.status = 0;
        setImage(new Image("file:" + "images/default.png"));
        this.event_id = gen_id;
    }
    public void addGenId(){
        gen_id++;
    }

    public boolean isEventId(int event_id){
        return this.event_id == event_id;
    }
    public void addTeam(Team team){
        this.teams.add(team);
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
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

    public void setDateStart(String dateStart) {
        this.dateStart = LocalDate.parse(dateStart);
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = LocalDate.parse(dateEnd);
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = LocalTime.parse(timeStart);
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = LocalTime.parse(timeEnd);
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setCapacity(int maxMember) {
        this.maxMember = maxMember;
    }

    public ArrayList<Team> getTeam() {
        return teams;
    }

    public void setTeam(ArrayList<Team> team) {
        this.teams = team;
    }

    public ArrayList<User> getUser() {
        return users;
    }

    public void setUser(ArrayList<User> user) {
        this.users = user;
    }

    public int getCountMember() {
        return countMember;
    }

    public void addCountMember() {
        this.countMember++;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public static int getGen_id() {
        return gen_id;
    }

    public int getEvent_id() {
        return event_id;
    }
}
