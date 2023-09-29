package cs211.project.controllers;

import cs211.project.models.Activity;
import cs211.project.models.ActivityList;
import cs211.project.pivot.EventActivity;
import cs211.project.pivot.EventActivityList;
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

public class EventActivityController {

    @FXML private TableView<Activity> activityTableView;
    @FXML private AnchorPane anchorPane;



    private ActivityTeamEventRepository TeamEventrepository;

    private  ActivityRepository repository;

    private ActivityList activitys;
    private  ActivityList ShowActivitys;

    private  Activity activity;

    private EventActivityList eventActivity;

    @FXML
    public void initialize() {
        TeamEventrepository = new ActivityTeamEventRepository();
        ShowActivitys = new ActivityList();
        eventActivity = TeamEventrepository.getEventActivity();
        repository = new ActivityRepository();
        activitys = repository.getActivityList();
        for (EventActivity event : eventActivity.getList()) {
            activity = activitys.findActivityById(event.getActivity_id());
            activity.checkTimeActivity();
            ShowActivitys.addNewActivityFromFile(activity.getName(), activity.getDetail(), "" + activity.getId(), "" + activity.getDateStart(), "" + activity.getDateEnd(), "" + activity.getTimeStart(), "" + activity.getTimeEnd(), activity.getStatus());
        }


        showTable(ShowActivitys);

        activityTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Activity>() {
            @Override
            public void changed(ObservableValue<? extends Activity> observable, Activity oldValue, Activity newValue) {
                if (newValue != null) {
                    try {

                        NPBPRouter.loadPage("Edit Activity", anchorPane, newValue);
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


        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        activityTableView.getColumns().clear();
        activityTableView.getColumns().add(nameColumn);
        activityTableView.getColumns().add(StartDateColumn);
        activityTableView.getColumns().add(StartTimeColumn);
        activityTableView.getColumns().add(EndDateColumn);
        activityTableView.getColumns().add(EndTimeColumn);
        activityTableView.getItems().clear();

        // ใส่ข้อมูล Student ทั้งหมดจาก studentList ไปแสดงใน TableView
        for (Activity activity: activityList.getActivity()) {
            activityTableView.getItems().add(activity);
        }
    }

    public void delete(){}

    public void create(){}
}