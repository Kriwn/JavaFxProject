package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.EventActivity;
import cs211.project.pivot.EventActivityList;
import cs211.project.pivot.TeamActivity;
import cs211.project.pivot.TeamActivityList;
import cs211.project.repository.ActivityRepository;
import cs211.project.repository.ActivityTeamEventRepository;
import cs211.project.repository.TeamRepository;
import cs211.project.services.NPBPRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TeamActivityController {

    @FXML
    private TableView<Activity> activityTableView;

    @FXML
    private AnchorPane page;

    private ActivityTeamEventRepository TeamEventrepository;

    private ActivityRepository repository;

    private ActivityList activitys;
    private  ActivityList ShowActivitys;

    private  Activity activity;

    private TeamActivityList teamActivityList;

    private User user;

    private  int teamId;

    private int eventId;

    private TeamRepository teamRepository;


    private TeamList teamList;

    @FXML
    public void initialize() {
        TeamEventrepository = new ActivityTeamEventRepository();
        ShowActivitys = new ActivityList();
        teamActivityList  = TeamEventrepository.getTeamActivity();
        teamRepository = new TeamRepository();
        teamList = teamRepository.getTeamList();
        repository = new ActivityRepository();
        activitys = repository.getActivityList();
        user = (User) NPBPRouter.getDataAccount();
        teamId = (int) NPBPRouter.getDataTeam();
        eventId = (int)NPBPRouter.getDataEvent();


        for(TeamActivity team :  teamActivityList.getList()) {
            if (team.isTeamId(teamId)) {
                activity = activitys.findActivityById(team.getActivity_id());
                activity.checkTimeActivity();
                ShowActivitys.addNewActivityFromFile(activity.getName(), activity.getDetail(), "" + activity.getId(), "" + activity.getDateStart(), "" + activity.getDateEnd(), "" + activity.getTimeStart(), "" + activity.getTimeEnd(), "" + activity.getStatus());
            }
        }
        showTable(ShowActivitys);

        activityTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Activity>() {
            @Override
            public void changed(ObservableValue observable, Activity oldValue, Activity newValue) {
                if (newValue != null) {
                    try {
                        NPBPRouter.loadPageEditTeam("edit-team-activity",page,user,teamId,newValue.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
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
    public void create(){
        try {
            NPBPRouter.loadPage("create-team-activity",page,user,eventId,teamId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void back(){
        try {
            NPBPRouter.loadPage("team-detail",page,user,eventId,teamList.findTeamById(teamId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
