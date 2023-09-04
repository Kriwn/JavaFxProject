package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.Staff;
import cs211.project.repository.EventRepository;
import cs211.project.services.Datasource;
import cs211.project.services.EventDatasource;
import cs211.project.services.NPBPRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private TextField detailsEvent;

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
    private Event eventForSetImagePath;
    private Event selectEvent;
    private ArrayList<Event> event;

    public void initialize(){
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        event = eventRepository.getEvents().getEvents();
    }

    @FXML
    public void handleCreateEventButton() {
        String nameString = nameEvent.getText().trim();
        String detailsString = detailsEvent.getText().trim();
        String dateStart = dateStartEvent.getValue().toString();
        String dateEnd = dateEndEvent.getValue().toString();
        String timeStart = timeStartEvent.getText().trim();
        String timeEnd = timeEndEvent.getText().trim();
        String maxMember = capacityEvent.getText().trim();
        Image image = eventImageView.getImage();

        eventList.addNewEvent(nameString,detailsString,dateStart,dateEnd,timeStart,timeEnd,"0",maxMember,image);
        nameEvent.clear();
        detailsEvent.clear();
        timeStartEvent.clear();
        timeEndEvent.clear();
        dateStartEvent.setValue(null);
        dateEndEvent.setValue(null);
        capacityEvent.clear();
        eventRepository.save(eventList);


        try {
            NPBPRouter.loadPage("my-create-event",page);
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
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());

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
