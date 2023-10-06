package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.AccountEventList;
import cs211.project.pivot.EventActivity;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.ActivityRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.Datasource;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML private TableView<Activity> activityTableView;

    private ActivityRepository repository;
    private ActivityList activitys;
    private  ActivityList ShowActivitys;

    private  Activity activity;

    private  ArrayList<Integer> results;

    private AccountEventList accountEventList;

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
        accountEventList = accountEventRepository.getList_join();
        results = accountEventList.findEventsByAccount(user.getAccountId());
        ShowActivitys = new ActivityList();
        repository = new ActivityRepository();
        activitys = repository.getActivityList();

        for(var i:  results) {
                activity = activitys.findActivityById(i);
                activity.checkTimeActivity();
                ShowActivitys.addNewActivityFromFile(activity.getName(), activity.getDetail(), "" + activity.getId(), "" + activity.getDateStart(), "" + activity.getDateEnd(), "" + activity.getTimeStart(), "" + activity.getTimeEnd(), "" + activity.getStatus());
        }

        showTable(ShowActivitys);
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

    private void showTable(ActivityList activityList) {
        TableColumn<Activity, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Activity, String> StartDateColumn = new TableColumn<>("dateStart");
        StartDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateStart"));

        TableColumn<Activity, String> StartTimeColumn = new TableColumn<>("timeStart");
        StartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));

        TableColumn<Activity, String> EndDateColumn = new TableColumn<>("dateEnd");
        EndDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));

        TableColumn<Activity, String> EndTimeColumn = new TableColumn<>("timeEnd");
        EndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));

        TableColumn<Activity, String> StatusColumn = new TableColumn<>("status");
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        activityTableView.getColumns().clear();
        activityTableView.getColumns().add(nameColumn);
        activityTableView.getColumns().add(StartDateColumn);
        activityTableView.getColumns().add(StartTimeColumn);
        activityTableView.getColumns().add(EndDateColumn);
        activityTableView.getColumns().add(EndTimeColumn);
        activityTableView.getColumns().add(StatusColumn);
        activityTableView.getItems().clear();

        for (Activity activity: activityList.getActivity()) {
            activityTableView.getItems().add(activity);
        }
    }
}
