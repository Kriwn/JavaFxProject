package cs211.project.controllers;

import cs211.project.models.Activity;
import cs211.project.models.ActivityList;
import cs211.project.models.User;
import cs211.project.pivot.EventActivity;
import cs211.project.pivot.EventActivityList;
import cs211.project.pivot.TeamActivity;
import cs211.project.pivot.TeamActivityList;
import cs211.project.repository.ActivityRepository;
import cs211.project.repository.ActivityTeamEventRepository;
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

    @FXML
    public void initialize() {
        TeamEventrepository = new ActivityTeamEventRepository();
        ShowActivitys = new ActivityList();
        teamActivityList  = TeamEventrepository.getTeamActivity();
        repository = new ActivityRepository();
        activitys = repository.getActivityList();
        user = (User) NPBPRouter.getDataAccount();
        teamId = (int) NPBPRouter.getDataTeam();

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
                        NPBPRouter.loadPage("edit-team-activity",page,user,teamId,newValue.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
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
    public void create(){
        try {
            NPBPRouter.loadPage("create-team-activity",page,user,teamId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
