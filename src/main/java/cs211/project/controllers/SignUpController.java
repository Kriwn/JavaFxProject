package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable{
    @FXML AnchorPane signUpArea;

    public void initialize(URL location, ResourceBundle resources){
    }
    public void clickBack(MouseEvent event) throws IOException {
        NPBPRouter.loadPage("login", signUpArea);
    }

    @FXML public void changeTheme() {
        String css = getClass().getResource("css/theme-1.css").toExternalForm();
        Scene scene = signUpArea.getScene();
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(css);
    }
}
