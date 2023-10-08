package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class JoinEventController {

    @FXML
    private Label countEmpty;

    @FXML
    private Label countTotal;

    @FXML
    private Label dateEndEventLabel;

    @FXML
    private Label dateStartEventLabel;

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

    @FXML
    private TextArea detailsEvent;
    private EventList eventList;
    private Event event;

    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private AccountEventList accountEventList;
    private User user;
    public void initialize(){
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        user = (User) NPBPRouter.getDataAccount();
        int eventId = (Integer) NPBPRouter.getDataEvent();
        event = eventList.findEventById(eventId);
        showEvent(event);
        accountEventRepository = new AccountEventRepository();
        user.addMyEventFromFile(accountEventRepository.getList_join().findEventsByAccount(user.getAccountId()));
        detailsEvent.setEditable(false);
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
        detailsEvent.setText(details);
        dateStartEventLabel.setText(""+event.getDateStartEvent());
        dateEndEventLabel.setText(""+event.getDateEndEvent());
        timeStartEventLabel.setText(""+event.getTimeStartEvent());
        timeEndEventLabel.setText(""+event.getTimeEndEvent());
        countEmpty.setText(""+event.getCountMember());
        countTotal.setText(""+event.getMaxMember());
        eventImageView.setImage(new Image(event.getImage().getUrl()));
    }

    public void onJointEventButton(){
        event.addCountMember();
        eventRepository.save(eventList);
        accountEventList = accountEventRepository.getList_join();
        user.addMyEvent(event.getEventId());
        accountEventList.addNew(user.getAccountId(), event.getEventId());
        accountEventRepository.saveEventJoin(accountEventList);

        try {
            NPBPRouter.loadPage("home-page",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToSelectTeam(){
        try {
            NPBPRouter.loadPage("select-team-to-join",page,user,event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
* อาจจะส่งข้อมูลด้วยString array ของ account_id,event_id ที่ต้องการ join
* */
