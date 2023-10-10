package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.EventActivity;
import cs211.project.pivot.EventActivityList;
import cs211.project.repository.*;
import cs211.project.services.Datasource;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
    private Activity activity;

    private  ActivityList showActivitys;

    private  ActivityList activitys;

    private  EventActivityList eventActivityList;

    private EventActivityList eventActivity;
    private ActivityTeamEventRepository teamEventRepository;

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


        teamEventRepository = new ActivityTeamEventRepository();
        showActivitys = new ActivityList();
        repository = new ActivityRepository();
        activitys = repository.getActivityList();
        eventActivityList = new EventActivityList();
        eventActivity  = teamEventRepository.getEventActivity();

        for(EventActivity event :  eventActivity.getList()) {
            if (event.isEventId(eventId)) {
                activity = activitys.findActivityById(event.getActivityId());
                activity.checkTimeActivity();
                showActivitys.addNewActivityFromFile(activity.getName(), activity.getDetail(), "" + activity.getId(), "" + activity.getDateStart(), "" + activity.getDateEnd(), "" + activity.getTimeStart(), "" + activity.getTimeEnd(), "" + activity.getStatus());
            }
        }

        showTable(showActivitys);
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
        nameColumn.setResizable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Activity, String> startDateColumn = new TableColumn<>("dateStart");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        startDateColumn.setResizable(false);


        TableColumn<Activity, String> startTimeColumn = new TableColumn<>("timeStart");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
        startTimeColumn.setResizable(false);

        TableColumn<Activity, String> endDateColumn = new TableColumn<>("dateEnd");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        endDateColumn.setResizable(false);

        TableColumn<Activity, String> endTimeColumn = new TableColumn<>("timeEnd");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
        endTimeColumn.setResizable(false);

        TableColumn<Activity, String> statusColumn = new TableColumn<>("status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setResizable(false);

        activityTableView.getColumns().clear();
        activityTableView.getColumns().add(nameColumn);
        activityTableView.getColumns().add(startDateColumn);
        activityTableView.getColumns().add(startTimeColumn);
        activityTableView.getColumns().add(endDateColumn);
        activityTableView.getColumns().add(endTimeColumn);
        activityTableView.getColumns().add(statusColumn);
        activityTableView.getItems().clear();

        for (Activity activity: activityList.getActivity()) {
            activityTableView.getItems().add(activity);
        }
    }
}
