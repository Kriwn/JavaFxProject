package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.services.Datasource;
import cs211.project.services.EventDatasource;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class JoinEventController {
    private Datasource<EventList> datasource;
    private EventList eventList;
    private ArrayList<Event> events;
    private Event event;


    @FXML
    private Label countEmpty;

    @FXML
    private Label countTotal;

    @FXML
    private Label dateEndEventLabel;

    @FXML
    private Label dateStartEventLabel;

    @FXML
    private Label detailsEventLabel;

    @FXML
    private Label nameEventLabel;

    @FXML
    private AnchorPane page;

    @FXML
    private Label timeEndEventLabel;

    @FXML
    private Label timeStartEventLabel;
    @FXML
    private ImageView eventImageView;
    public void initialize(){
        datasource = new EventDatasource("data", "event.csv");
        eventList = datasource.readData();
        String nameEvent = (String) NPBPRouter.getData();
        event = eventList.findEventByName(nameEvent);

        showEvent(event);
    }

    public void showEvent(Event event){
        nameEventLabel.setText(event.getName());
        detailsEventLabel.setText(event.getDetail());
        dateStartEventLabel.setText(""+event.getDateStart());
        dateEndEventLabel.setText(""+event.getDateEnd());
        timeStartEventLabel.setText(""+event.getTimeStart());
        timeEndEventLabel.setText(""+event.getTimeEnd());
        countEmpty.setText(""+event.getCountMember());
        countTotal.setText(""+event.getMaxMember());
        eventImageView.setImage(new Image(event.getImage().getUrl()));
    }

    public void onJointEventButton(){
        event.addCountMember();
        try {
            NPBPRouter.loadPage("home-page",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
