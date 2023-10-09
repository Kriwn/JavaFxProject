package cs211.project.controllers;

import cs211.project.models.ActivityList;
import cs211.project.models.Event;
import cs211.project.models.User;
import cs211.project.pivot.EventActivityList;
import cs211.project.pivot.TeamActivityList;
import cs211.project.repository.ActivityRepository;
import cs211.project.repository.ActivityTeamEventRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateTeamActivityController implements Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private AnchorPane page;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private  DatePicker endDatePicker;

    @FXML
    private TextArea detailTextArea;

    @FXML
    private  TextField timeStart;

    @FXML
    private  TextField timeEnd;

    private ActivityRepository activityRepository;
    private ActivityTeamEventRepository activityTeamEventRepository;

    private TeamActivityList teamActivityList;

    private ActivityList activityList;

    private int eventId;

    private  int teamId;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activityRepository = new ActivityRepository();
        activityList = activityRepository.getActivityList();
        activityTeamEventRepository = new ActivityTeamEventRepository();
        teamActivityList = activityTeamEventRepository.getTeamActivity();
        user = (User) NPBPRouter.getDataAccount();
        teamId = (int)NPBPRouter.getDataTeam();
        eventId = (int)NPBPRouter.getDataEvent();
    }


    @FXML
    public  void  createActivity(){
        String name = nameTextField.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String detail = detailTextArea.getText();
        String startTime = timeStart.getText();
        String endTime =timeEnd.getText();
        activityList.addNewActivity(name,detail,startDate.toString(),endDate.toString(),startTime,endTime);
        activityRepository.save(activityList);
        teamActivityList.addNew(teamId,activityList.getLastId());
        activityTeamEventRepository.saveTeam(teamActivityList);
        backToEventActivity();
    }


    public void backToEventActivity(){
        try {
            NPBPRouter.loadPage("team-activity",page,user,eventId,teamId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
