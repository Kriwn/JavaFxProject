package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SettingController {

    private User user;
    @FXML private TextField oldTextField;
    @FXML private TextField newTextField;
    @FXML private TextField conTextField;
    @FXML private Label errorLabel;

    public void initialize(){
        user = (User) NPBPRouter.getDataAccount();
        errorLabel.setText("");
    }

    @FXML
    public void chooseImageButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String name =file.getName();
            String separator = name.substring(name.lastIndexOf('.'), name.length());
            // Define the destination directory and file name where you want to save the selected file
            File destinationDir = new File("images/User");
            String destinationFileName = user.getUsername() + separator;

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

    public void confirm(){
        String oldpass = oldTextField.getText().trim();
        String newpass = newTextField.getText().trim();
        String conpass = conTextField.getText().trim();
        if (oldpass.equals("") || newpass.equals("") || conpass.equals(""))
            errorLabel.setText("Please fill  is required");
            return ;
    }
}
