package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.services.Datasource;
import cs211.project.services.EventDatasource;
import cs211.project.services.NPBPRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class CreateEventController {
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
    private Datasource<EventList> datasource;
    private Event event ;
    private Event eventForSetImagePath;

    public void initialize(){
        datasource = new EventDatasource("data","event.csv");
        eventList = datasource.readData();
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
        eventList.addNewEvent(nameString,detailsString,dateStart,dateEnd,timeStart,timeEnd,maxMember);
        nameEvent.clear();
        detailsEvent.clear();
        timeStartEvent.clear();
        timeEndEvent.clear();
        dateStartEvent.setValue(null);
        dateEndEvent.setValue(null);
        capacityEvent.clear();
        datasource.writeData(eventList);
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
            try {

                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images");

                if (!destDir.exists()) destDir.mkdirs();


                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");

                String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );


                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);


                // SET NEW FILE PATH TO IMAGE
                eventImageView.setImage(new Image(target.toUri().toString()));

                //setImagePath
                Event eventForSetImagePath = new Event("nameSetImage","detailsSetImage","dateStartSetImage","dateEndSetImage","timeStartSetImage","timeEndSetImage","0");
                eventForSetImagePath.setImage(destDir + "/" + filename);
                this.eventForSetImagePath=eventForSetImagePath;
//                System.out.println("Upload: "+accountForSetImagePath.getImagePath())
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
