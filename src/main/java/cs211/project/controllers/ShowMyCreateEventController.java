package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowMyCreateEventController implements Initializable {
    @FXML
    private AnchorPane page;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private VBox vbox;
    private User user;
    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private EventList eventList;
    private ArrayList<Event> events;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        events = eventList.getEvents();
        accountEventRepository = new AccountEventRepository();
        AccountEventList list_create = accountEventRepository.getList_create();
        ArrayList<Integer> listId = new ArrayList<>();
        listId.addAll(list_create.findEventsByAccount(user.getAccountId()));
        ArrayList<Event> eventCreate = new ArrayList<>();
        for (var i : listId){
            eventCreate.add(eventList.findEventById(i));
        }
        for (var i : eventCreate){
            vbox.getChildren().add(createCard(i));
        }
    }

    public VBox createCard(Event newEvent){
        File file = new File("src/main/resources/cs211/project/views/event-card.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        VBox vbox = null;
        try {
            vbox = (VBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EventController eventController = (EventController) fxmlLoader.getController();
        Label namelabel = eventController.getNameEventLabel();
        Label countMember = eventController.getCountMemberLabel();
        Label maxMember = eventController.getMaxMemberLabel();
        Circle img = eventController.getImgCircle();

        namelabel.setText(newEvent.getName());
        countMember.setText(""+newEvent.getCountMember());
        maxMember.setText(""+newEvent.getMaxMember());
//        img.setFill(new ImagePattern(new Image(newEvent.getImage().getUrl())));

        img.setFill(new ImagePattern(new Image("file:"+"images/"+"default.png")));
        vbox.setOnMouseClicked(event ->{
            try {
                NPBPRouter.loadPage("my-create-event",page,user,newEvent.getEventId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return vbox;
    }
}
