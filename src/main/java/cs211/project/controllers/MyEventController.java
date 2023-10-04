package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.Datasource;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyEventController implements Initializable {
    @FXML
    private Label dateEndLabel;

    @FXML
    private Label dateStartLabel;

    @FXML
    private Label nameEventLabel;

    @FXML
    private AnchorPane page;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label timeEndLabel;

    @FXML
    private Label timeStartLabel;

    @FXML
    private VBox vbox;
    @FXML
    private TextArea detailsTextArea;

    private Datasource<AccountList> datasourceAccount;
    private AccountList users;
    private User user;
    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private AccountRepository accountRepository;
    private EventList eventList;
    private ArrayList<Event> events;
    private Event event;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        accountRepository = new AccountRepository();
        eventList = eventRepository.getEvents();
        events = eventList.getEvents();
        accountEventRepository = new AccountEventRepository();
        int eventId = (Integer) NPBPRouter.getDataEvent();
        event = eventList.findEventById(eventId);
        detailsTextArea.setEditable(false);

        showEvent(event);
    }

    public void showEvent(Event event){
        nameEventLabel.setText(event.getName());
        String details = event.getDetail();
        String []details_new = details.split("\\|");
        details = "";
        for (var i : details_new){
            details += i.trim();
            details += "\n";
        }
        detailsTextArea.setText(details);
        dateStartLabel.setText(event.getDateStartEvent().toString());
        timeStartLabel.setText(event.getTimeEndEvent().toString());
        dateEndLabel.setText(event.getDateEndEvent().toString());
        timeEndLabel.setText(event.getTimeEndEvent().toString());
    }
}
