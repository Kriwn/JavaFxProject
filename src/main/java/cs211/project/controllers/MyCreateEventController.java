package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyCreateEventController implements Initializable {
    @FXML
    AnchorPane page;

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameEvent;
    private EventRepository eventRepository;
    private EventList eventList;
    private ArrayList<Event> events;
    private AccountRepository accountRepository;
    private User user;
    private Event event;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        user = (User) NPBPRouter.getDataAccount();
        int eventId = (Integer) NPBPRouter.getDataEvent();
        event = eventList.findEventById(eventId);

        showEvent(event);
    }

    public void showEvent(Event event){
        imageView.setImage(new Image(event.getImage().getUrl()));
        nameEvent.setText(event.getName());
    }
    public void goToStaffList(){
        try {
            NPBPRouter.loadPage("staff-list",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goToChat(){
        try {
            NPBPRouter.loadPage("chat",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goToEditUser(){
        try {
            NPBPRouter.loadPage("edit-user",page,user,event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToEditEvent(){
        try {
            NPBPRouter.loadPage("edit-event",page,user,event.getEventId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
