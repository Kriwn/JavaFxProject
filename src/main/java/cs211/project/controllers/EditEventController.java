package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class EditEventController implements Initializable {
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
    private AnchorPane page;

    @FXML
    private TextField timeEndEvent;

    @FXML
    private TextField timeStartEvent;
    @FXML
    private Label successLabel;

    private User user;
    private Event event;
    private EventRepository eventRepository;
    private EventList eventList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User) NPBPRouter.getDataAccount();
        int eventId = (int) NPBPRouter.getDataEvent();
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        event = eventRepository.findById(eventId);
        successLabel.setVisible(false);
        showText();

        nameEvent.textProperty().addListener((observableValue, old, New) -> {
            if(New != null) {
                successLabel.setVisible(false);
            }
        });

        detailsTextArea.textProperty().addListener((observableValue, old, New) -> {
            if(New != null) {
                successLabel.setVisible(false);
            }
        });

        dateStartEvent.promptTextProperty().addListener(((observableValue, old, New) -> {
            if(New != null){
                successLabel.setVisible(false);
            }
        }));

        dateEndEvent.promptTextProperty().addListener(((observableValue, old, New) -> {
            if(New != null){
                successLabel.setVisible(false);
            }
        }));

        capacityEvent.textProperty().addListener((observableValue, old, New) -> {
            if(New != null) {
                successLabel.setVisible(false);
            }
        });

        timeStartEvent.textProperty().addListener((observableValue, old, New) -> {
            if(New != null) {
                successLabel.setVisible(false);
            }
        });

        timeEndEvent.textProperty().addListener((observableValue, old, New) -> {
            if(New != null) {
                successLabel.setVisible(false);
            }
        });
    }

    public void showText(){
        nameEvent.setText(event.getName());
        String details = event.getDetail();
        String []details_new = details.split("\\|");
        details = "";
        for (var i : details_new){
            details += i.trim();
            details += "\n";
        }
        detailsTextArea.setText(details);

        dateStartEvent.setValue(event.getDateStartEvent());
        timeStartEvent.setText(event.getTimeStartEvent().toString());
        dateEndEvent.setValue(event.getDateEndEvent());
        timeEndEvent.setText(event.getTimeEndEvent().toString());
        eventImageView.setImage(event.getImage());
        capacityEvent.setText(""+event.getMaxMember());
    }

    public void handleEditEventButton(){
        String name = nameEvent.getText();
        String dateStart = dateStartEvent.getValue().toString();
        String timeStart = timeStartEvent.getText();
        String dateEnd = dateEndEvent.getValue().toString();
        String timeEnd = timeEndEvent.getText();
        Image image = eventImageView.getImage();
        String maxMember = capacityEvent.getText();

        String []s = detailsTextArea.getText().split("\n");
        String details = "";
        for (var i : s){
            details += i.trim();
            details += "|";
        }

        event.editEvent(name, details, dateStart, timeStart, dateEnd, timeEnd, maxMember, image);
        eventRepository.save(eventList);

        successLabel.setVisible(true);

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

            successLabel.setVisible(false);

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
