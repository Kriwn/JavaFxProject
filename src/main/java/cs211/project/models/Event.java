package cs211.project.models;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private int eventId;
    private String name;
    private String detail;
    private  int countMember;
    private int maxMember;
    private Image image;
    private LocalDate dateStartEvent;
    private LocalDate dateEndEvent;
    private LocalTime timeStartEvent;
    private LocalTime timeEndEvent;

    private LocalDate openDateStart;
    private LocalDate openDateEnd;
    private LocalTime openTimeStart;
    private LocalTime openTimeEnd;
    private boolean statusEvent;
    private boolean statusJoin;

    public Event(String name, String id, String details,
                 String dateStartEvent, String dateEndEvent, String timeStartEvent, String timeEndEvent,
                 String openDateStart, String openDateEnd, String openTimeStart, String openTimeEnd,
                 String countMember, String maxMember, Image image){

        this.name = name;
        this.eventId = Integer.parseInt(id);
        this.detail = details;
        this.dateStartEvent = LocalDate.parse(dateStartEvent);
        this.dateEndEvent = LocalDate.parse(dateEndEvent);
        this.timeStartEvent = LocalTime.parse(timeStartEvent);
        this.timeEndEvent = LocalTime.parse(timeEndEvent);
        this.openDateStart = LocalDate.parse(openDateStart);
        this.openDateEnd = LocalDate.parse(openDateEnd);
        this.openTimeStart = LocalTime.parse(openTimeStart);
        this.openTimeEnd = LocalTime.parse(openTimeEnd);
        this.countMember = Integer.parseInt(countMember);
        this.maxMember = Integer.parseInt(maxMember);
        this.image = image;
    }
    public Event(String name, String id, String details,
                 String dateStartEvent, String dateEndEvent, String timeStartEvent, String timeEndEvent,
                 String countMember, String maxMember, Image image){

        this.name = name;
        this.eventId = Integer.parseInt(id);
        this.detail = details;
        this.dateStartEvent = LocalDate.parse(dateStartEvent);
        this.dateEndEvent = LocalDate.parse(dateEndEvent);
        this.timeStartEvent = LocalTime.parse(timeStartEvent);
        this.timeEndEvent = LocalTime.parse(timeEndEvent);
        this.openDateStart = LocalDate.now();
        this.openDateEnd = LocalDate.now();
        this.openTimeStart = LocalTime.now();
        this.openTimeEnd = LocalTime.now();
        this.countMember = Integer.parseInt(countMember);
        this.maxMember = Integer.parseInt(maxMember);
        this.image = image;
    }

    public Event(String name, String id, String details,
                 String dateStartEvent, String dateEndEvent, String timeStartEvent, String timeEndEvent,
                 String countMember, String maxMember){

        this.name = name;
        this.eventId = Integer.parseInt(id);
        this.detail = details;
        this.dateStartEvent = LocalDate.parse(dateStartEvent);
        this.dateEndEvent = LocalDate.parse(dateEndEvent);
        this.timeStartEvent = LocalTime.parse(timeStartEvent);
        this.timeEndEvent = LocalTime.parse(timeEndEvent);
        this.countMember = Integer.parseInt(countMember);
        this.maxMember = Integer.parseInt(maxMember);
    }

    public Event(String name, String id, String details,
                 String dateStartEvent, String dateEndEvent, String timeStartEvent, String timeEndEvent,
                 String openDateStart, String openDateEnd, String openTimeStart, String openTimeEnd,
                 String countMember, String maxMember){
        this.name = name;
        this.detail = details;
        this.maxMember = Integer.parseInt(maxMember);
        this.dateStartEvent = LocalDate.parse(dateStartEvent);
        this.dateEndEvent = LocalDate.parse(dateEndEvent);
        this.timeStartEvent = LocalTime.parse(timeStartEvent);
        this.timeEndEvent = LocalTime.parse(timeEndEvent);
        this.openDateStart = LocalDate.parse(openDateStart);
        this.openDateEnd = LocalDate.parse(openDateEnd);
        this.openTimeStart = LocalTime.parse(openTimeStart);
        this.openTimeEnd = LocalTime.parse(openTimeEnd);
        this.countMember = Integer.parseInt(countMember);
        this.statusEvent = true;
        setImage(new Image("file:" + "images/default.png"));
        this.eventId = Integer.parseInt(id);
    }

    public void checkTimeEvent(){
        if(dateEndEvent.isAfter(LocalDate.now())){
            if(dateEndEvent.equals(LocalDate.now())){
                if(timeEndEvent.isAfter(LocalTime.now())){
                    statusEvent = true;
                }
                else{
                    statusEvent = false;
                }
            }
            else{
                statusEvent = true;
            }
        }
        else{
            statusEvent = false;
        }
    }

    public void checkTimeJoin(){
        if(openDateEnd.isAfter(LocalDate.now()) && openDateStart.isBefore(LocalDate.now())){
            if(openDateEnd.equals(LocalDate.now()) || openDateStart.equals(LocalDate.now())){
                if(openTimeEnd.isAfter(LocalTime.now()) && openTimeStart.isBefore(LocalTime.now())){
                    statusJoin = true;
                }
                else{
                    statusJoin = false;
                }
            }
            else{
                statusJoin = true;
            }
        }
        else{
            statusJoin = false;
        }
    }

    public boolean checkMember(){
        if (countMember < maxMember){
            return true;
        }
        return false;
    }

    public void editEvent(String name, String details, String dateStart, String timeStart, String dateEnd, String timeEnd, String maxMember, Image image){
        this.name = name;
        this.detail = details;
        this.dateStartEvent = LocalDate.parse(dateStart);
        this.timeStartEvent = LocalTime.parse(timeStart);
        this.dateEndEvent = LocalDate.parse(dateEnd);
        this.timeEndEvent = LocalTime.parse(timeEnd);
        this.maxMember = Integer.parseInt(maxMember);
        this.image = image;
    }

    public boolean isEventId(int event_id){
        return this.eventId == event_id;
    }

    public LocalDate getDateStartEvent() {
        return dateStartEvent;
    }

    public LocalDate getDateEndEvent() {
        return dateEndEvent;
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

    public void setDateStartEvent(String dateStartEvent) {
        this.dateStartEvent = LocalDate.parse(dateStartEvent);
    }

    public void setDateEndEvent(String dateEndEvent) {
        this.dateEndEvent = LocalDate.parse(dateEndEvent);
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTimeStartEvent(String timeStartEvent) {
        this.timeStartEvent = LocalTime.parse(timeStartEvent);
    }

    public LocalTime getTimeEndEvent() {
        return timeEndEvent;
    }

    public LocalTime getTimeStartEvent() {
        return timeStartEvent;
    }

    public void setTimeEndEvent(String timeEndEvent) {
        this.timeEndEvent = LocalTime.parse(timeEndEvent);
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setCapacity(int maxMember) {
        this.maxMember = maxMember;
    }

    public int getCountMember() {
        return countMember;
    }

    public void addCountMember() {
        this.countMember++;
    }

    public boolean getStatusEvent() {
        return statusEvent;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public int getEventId() {
        return eventId;
    }

    public LocalDate getOpenDateStart() {
        return openDateStart;
    }

    public LocalDate getOpenDateEnd() {
        return openDateEnd;
    }

    public LocalTime getOpenTimeStart() {
        return openTimeStart;
    }

    public LocalTime getOpenTimeEnd() {
        return openTimeEnd;
    }

    public boolean getStatusJoin() {
        return statusJoin;
    }

    public void setStatusJoin(boolean statusJoin) {
        this.statusJoin = statusJoin;
    }

    public void setOpenDateStart(LocalDate openDateStart) {
        this.openDateStart = openDateStart;
    }

    public void setOpenDateEnd(LocalDate openDateEnd) {
        this.openDateEnd = openDateEnd;
    }

    public void setOpenTimeStart(LocalTime openTimeStart) {
        this.openTimeStart = openTimeStart;
    }

    public void setOpenTimeEnd(LocalTime openTimeEnd) {
        this.openTimeEnd = openTimeEnd;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", countMember=" + countMember +
                ", maxMember=" + maxMember +
                ", image=" + image +
                ", dateStartEvent=" + dateStartEvent +
                ", dateEndEvent=" + dateEndEvent +
                ", timeStartEvent=" + timeStartEvent +
                ", timeEndEvent=" + timeEndEvent +
                ", openDateStart=" + openDateStart +
                ", openDateEnd=" + openDateEnd +
                ", openTimeStart=" + openTimeStart +
                ", openTimeEnd=" + openTimeEnd +
                ", statusEvent=" + statusEvent +
                ", statusJoin=" + statusJoin +
                '}';
    }
}
