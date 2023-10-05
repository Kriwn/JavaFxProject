package cs211.project.controllers;

import cs211.project.models.Activity;
import cs211.project.models.ActivityList;
import cs211.project.models.User;
import cs211.project.pivot.EventActivityList;
import cs211.project.pivot.TeamActivityList;
import cs211.project.repository.ActivityRepository;
import cs211.project.repository.ActivityTeamEventRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditTeamActivityController {
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

    private ActivityList activityList;

    private Activity activity;

    private  int id;

    private  int teamId;

    private User user;
    public void initialize(URL url, ResourceBundle resourceBundle){
        activityRepository = new ActivityRepository();
        activityList = activityRepository.getActivityList();
        id = (int) NPBPRouter.getDataActivity();
        teamId = (int)NPBPRouter.getDataTeam();
        user = (User)NPBPRouter.getDataAccount();
        activity = activityList.findActivityById(id);


        // edit event
        nameTextField.setText(activity.getName());
        startDatePicker.setValue(activity.getDateStart());
        endDatePicker.setValue(activity.getDateEnd());
        detailTextArea.setText(activity.getDetail());
        timeStart.setText((""+activity.getTimeStart()));
        timeEnd.setText(""+activity.getTimeEnd());
    }

    @FXML
    public  void  changeActivity(){
        String name = nameTextField.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String detail = detailTextArea.getText();
        String startTime = timeStart.getText();
        String endTime =timeEnd.getText();
        activity.editActivity(name,detail,startDate,endDate,startTime,endTime);
        activityRepository.save(activityList);
        backToEventActivity();
    }

    public void delete(){
//        activityList.remove(activityList.findActivityById(id));
//        activityRepository.save(activityList);

//        eventActivityList.remove(teamId,id);
//        activityTeamEventRepository.saveTeam(eventActivityList);
    }


    public void backToEventActivity(){
        try {
            NPBPRouter.loadPage("team-activity",page,user,teamId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
