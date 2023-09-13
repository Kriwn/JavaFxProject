package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SettingController {
    private AccountList userList;

    private User user;

    @FXML
    public void initialize(){
        // read csv
        //user = userList.findUserByUsername("stttt");
        user = new User( "username",  "name");
    }

    public void chooseImageButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String name =file.getName();
            String separator = name.substring(name.lastIndexOf('.'), name.length());
            // Define the destination directory and file name where you want to save the selected file
            File destinationDir = new File("images/User");
            String destinationFileName = user.getName() + separator;

            // Create the destination file
            File destinationFile = new File(destinationDir, destinationFileName);

            // Copy the selected file to the destination
            try {
                Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
