package cs211.project.repository;

import cs211.project.models.EventList;
import cs211.project.services.Datasource;
import cs211.project.services.EventDatasource;

public class EventRepository {
    private EventList events;
    private Datasource<EventList> datasource;

    public EventRepository(){
        datasource = new EventDatasource("data","event.csv");
        events = datasource.readData();
    }

    public void save(EventList eventList){
        datasource.writeData(eventList);
    }

    public EventList getEvents(){
        return events;
    }
}
