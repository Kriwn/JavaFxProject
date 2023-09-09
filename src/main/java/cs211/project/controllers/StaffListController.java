package cs211.project.controllers;

import cs211.project.models.Staff;
import cs211.project.models.StaffList;
import cs211.project.services.Datasource;
import cs211.project.services.NPBPRouter;
import cs211.project.services.StaffDatasource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class StaffListController {
    private ArrayList<Staff> staffs;
    private StaffList staffList;
    private Datasource<StaffList> datasource;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    private Staff selectStaff;
    private VBox selectBox;
    @FXML
    AnchorPane page;
    public void initialize(){
        datasource = new StaffDatasource("staffs", "staffs.csv");
        staffList = datasource.readData();
        staffs = staffList.getStaffs();
        //datasource.writeData(staffList);
        int count=0;

        for(var staff : staffs){
            if(count%3==0){vbox1.getChildren().add(createCard(staff));}
            if(count%3==1){vbox2.getChildren().add(createCard(staff));}
            if(count%3==2){vbox3.getChildren().add(createCard(staff));}
            count++;
        }
    }

    public VBox createCard(Staff staff) {
        File file = new File("src/main/resources/cs211/project/views/staff-card.fxml");
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

        StaffController staffController = (StaffController) fxmlLoader.getController();
        Label usernameLabel = staffController.getUsernameLabel();
        Label statusLabel = staffController.getStatusLabel();
        Label roleLabel = staffController.getRoleLabel();
        Circle img = staffController.getImgCircle();

        usernameLabel.setText(staff.getUsername());
        roleLabel.setText(staff.getRole());
        statusLabel.setText(staff.getStatus());
        img.setFill(new ImagePattern(new Image("file:" + staff.getImagePath())));

        VBox finalVbox = vbox;
        vbox.setOnMouseClicked(event -> {
            selectStaff = staff;
            selectBox = finalVbox;
            System.out.println("Selected Staff: " + selectStaff.getUsername()); // for debugging
        });

        vbox.setOnDragOver(event -> handleDragOver(event));
        vbox.setOnDragDropped(event -> handleDrop(event, staff));
        return vbox;
    }

    public void handleDragOver(DragEvent event){
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    public void handleDrop(DragEvent event, Staff staff) {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image("file:" + files.get(0).getAbsolutePath());
        System.out.println("ImagePath : " + files.get(0).getAbsolutePath());

        Circle circle = staff.getImg();
        circle.setFill(new ImagePattern(img));
        staff.setImg(circle);
        String path = writeFile(files.get(0), staff);
        staff.setImagePath(path);
        datasource.writeData(staffList);
        refresh();
    }

    public String writeFile(File file, Staff staff) {
        File dir = new File("src/main/resources/cs211/project/Images");

        String locate = dir.getParent();
        File f = new File(locate +"/Images");
        if (!f.exists()) {f.mkdirs();}

        Path from = Paths.get(file.toURI());

        String name = file.getName();
        String separator = name.substring(name.lastIndexOf('.'), name.length());
        String fileName = staff.getUsername();

        if (!separator.equals(".jpg") || !separator.equals(".png") || !separator.equals(".gif")){
            return staff.getImagePath();
        }

        Path to = Paths.get(locate+"/Images/"+fileName+separator);
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        try {
            Files.copy(from,to, options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return to.toString();
    }

    public void promote(){
        Staff staff = staffList.findStaffByUsername(selectStaff.getUsername());
        staff.setRole("Head");
        datasource.writeData(staffList);
        refresh();
    }
    public void demote(){
        Staff staff = staffList.findStaffByUsername(selectStaff.getUsername());
        staff.setRole("Member");
        datasource.writeData(staffList);
        refresh();
    }
    public void ban(){
        Staff staff = staffList.findStaffByUsername(selectStaff.getUsername());
        staff.setStatus("Ban");
        datasource.writeData(staffList);
        refresh();
    }
    public void unBan(){
        Staff staff = staffList.findStaffByUsername(selectStaff.getUsername());
        staff.setStatus("Normal");
        datasource.writeData(staffList);
        refresh();
    }

    public void refresh() {
        Circle circle = (Circle) selectBox.getChildren().get(1);
        Label username = (Label) selectBox.getChildren().get(3);
        Label role = (Label) selectBox.getChildren().get(4);
        Label status = (Label) selectBox.getChildren().get(5);

        circle.setFill(selectStaff.getImg().getFill());
        username.setText(selectStaff.getUsername());
        status.setText(selectStaff.getStatus());
        role.setText(selectStaff.getRole());

//        username.setText(selectStaff.getUsername());
//        status.setText(selectStaff.getStatus());
//        role.setText(selectStaff.getRole());
//        img.setFill(selectStaff.getImg().getFill());
//        vbox1.getChildren().clear();
//        vbox2.getChildren().clear();
//        vbox3.getChildren().clear();
//
//        staffList = datasource.readData();
//        staffs = staffList.getStaffs();
//
//        int count = 0;
//        for (var staff : staffs) {
//            if (count % 3 == 0) {
//                vbox1.getChildren().add(createCard(staff));
//            } else if (count % 3 == 1) {
//                vbox2.getChildren().add(createCard(staff));
//            } else if (count % 3 == 2) {
//                vbox3.getChildren().add(createCard(staff));
//            }
//            count++;
//        }
    }

    public void backToMyCreateEvent(){
        try {
            NPBPRouter.loadPage("my-create-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}