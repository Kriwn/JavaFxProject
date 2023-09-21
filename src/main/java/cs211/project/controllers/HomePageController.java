package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.AccountEvent;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPAnimation;
import cs211.project.services.NPBPRouter;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private AnchorPane page;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private VBox vbox;

    @FXML
    private ListView listView;

    private ArrayList<Event> events;
    private EventList eventList;
    private EventRepository eventRepository;
    private AccountList accountList;
    private AccountRepository accountRepository;
    private AccountEventRepository accountEventRepository;
    private User user;

    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        page.setOnMouseEntered(hover -> {
//            page.requestFocus();
//        });

        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        accountRepository = new AccountRepository();
        eventList = eventRepository.getEvents();
        events = eventList.getEvents();
        accountEventRepository = new AccountEventRepository();
        AccountEventList list_join = accountEventRepository.getList_join();
        AccountEventList list_create = accountEventRepository.getList_create();
        ArrayList<Integer> listId = new ArrayList<>();
        listId.addAll(list_join.findAllEventsByAccount(user.getAccountId()));
        listId.addAll(list_create.findEventsByAccount(user.getAccountId()));

        for (var i : listId){
            events.remove(eventList.findEventById(i));
        }
        showEvent(events);
        listView.setVisible(false);

        //----------------------* searchTextField *----------------------
        ObservableList<String> observableList = FXCollections.observableArrayList();
        searchTextField.textProperty().addListener((observableValue, old, New) -> {
            if(New.equals("")) {
                listView.getItems().clear();
                listView.setVisible(false);
                vbox.getChildren().clear();
                showEvent(events);
            }
            else if(New != null) {
                listView.setVisible(true);
                listView.getItems().clear();
                ArrayList<Event> list = new ArrayList<>();
                for(var i : events){
                    if(i.getName().toLowerCase().contains(New.toLowerCase())) {
                        observableList.add(i.getName());
                        list.add(eventList.findEventByName(i.getName()));
                    }
                }
                listView.setItems(observableList);
                vbox.getChildren().clear();
                showEvent(list);
            }
        });
        listView.setOnMouseClicked(click -> {
            if (!listView.getSelectionModel().getSelectedItems().isEmpty()) {
                String nameSelectEvent = listView.getSelectionModel().getSelectedItems().get(0).toString();
                Event selectEvent = eventList.findEventByName(nameSelectEvent);
                if (nameSelectEvent != null) {
                    vbox.getChildren().clear();
                    vbox.getChildren().add(createCard(selectEvent));
                    listView.setVisible(false);
                }
            }
        });
        //------------------------------------------------------------------

//        page.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
//            if (click.getCode() == KeyCode.F1) {
//                onCreateEventButton();
//            }
//        });
    }
    public void showEvent(ArrayList<Event> eventArrayList){
        for (var i : eventArrayList){
            i.checkTimeEvent();
            if (i.getStatus() && i.checkMember())
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
        img.setFill(new ImagePattern(new Image(newEvent.getImage().getUrl())));

//        img.setFill(new ImagePattern(new Image("file:"+"images/"+"default.png")));
        vbox.setOnMouseClicked(event ->{
            try {
                NPBPRouter.loadPage("join-event",page,user,newEvent.getEventId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        VBox finalVbox = vbox;
        vbox.setOnMouseEntered(event1 ->{
            NPBPAnimation.scaleTransition(finalVbox, 1.05);
        });

        vbox.setOnMouseExited(event2 ->{
            NPBPAnimation.reverseScale(finalVbox);
        });

        return vbox;
    }

    public void onCreateEventButton(){
        try {
            NPBPRouter.loadPage("create-event",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
