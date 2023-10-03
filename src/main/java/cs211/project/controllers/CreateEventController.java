package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateEventController {
    @FXML
    AnchorPane page;
    @FXML
    private TextField capacityEvent;

    @FXML
    private DatePicker dateEndEvent;

    @FXML
    private DatePicker dateStartEvent;

    @FXML
    private TextArea detailsTextArea;

    @FXML
    private ImageView eventImageView;

    @FXML
    private TextField nameEvent;

    @FXML
    private TextField timeEndEvent;

    @FXML
    private TextField timeStartEvent;
    private EventList eventList;
    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private AccountEventList accountEventList;
    private User user;
    private ArrayList<Event> event;

    public void initialize(){
        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        event = eventRepository.getEvents().getEvents();
        accountEventRepository = new AccountEventRepository();
        user.addMyCreateEventFromFile(accountEventRepository.getList_create().findEventsByAccount(user.getAccountId()));

        capacityEvent.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
            if (click.getCode() == KeyCode.ENTER) {
                handleCreateEventButton();
            }
        });
    }

    @FXML
    public void handleCreateEventButton() {
        String nameString = nameEvent.getText().trim();
        String dateStart = dateStartEvent.getValue().toString();
        String dateEnd = dateEndEvent.getValue().toString();
        String timeStart = timeStartEvent.getText().trim();
        String timeEnd = timeEndEvent.getText().trim();
        String maxMember = capacityEvent.getText().trim();
        Image image = eventImageView.getImage();


        String []s = detailsTextArea.getText().split("\n");
        String detailsString = "";
        for (var i : s){
            detailsString += i.trim();
            detailsString += "|";
        }

        eventList.createEvent(nameString,detailsString,dateStart,dateEnd,timeStart,timeEnd,"0",maxMember,image);
        Event exist = eventList.findEventByName(nameString);
        int event_id = exist.getEventId();
        accountEventList = accountEventRepository.getList_create();
        accountEventList.addNew(user.getAccountId(),event_id);
        accountEventRepository.saveEventOwner(accountEventList);
        nameEvent.clear();
        timeStartEvent.clear();
        timeEndEvent.clear();
        dateStartEvent.setValue(null);
        dateEndEvent.setValue(null);
        capacityEvent.clear();
        eventRepository.save(eventList);


        try {
            NPBPRouter.loadPage("my-create-event",page,user,event_id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleUploadImageButton(ActionEvent event) {
        FileChooser chooser = new FileChooser();

        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));


        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        File file = chooser.showOpenDialog(null);

        if (file != null) {

            File dir = new File("images");

            String locate = dir.getParent();
            File f = new File("images");
            if (!f.exists()) {f.mkdirs();}

            Path from = Paths.get(file.toURI());

            String name = file.getName();
            String separator = name.substring(name.lastIndexOf('.'), name.length());
            String fileName = nameEvent.getText().trim();

            eventImageView.setImage(new Image("file:"+"images/"+fileName+separator));

            Path to = Paths.get("images/"+fileName+separator);
            CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };
            try {
                Files.copy(from,to, options);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
