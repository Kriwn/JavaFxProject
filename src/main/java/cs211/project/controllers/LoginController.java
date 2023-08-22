package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{



    @FXML private AnchorPane loginArea; // right pane


    public void initialize(URL location, ResourceBundle resources){
    }

    public void clickSignIn(MouseEvent event) throws IOException {
        NPBPRouter.loadPage("signup", loginArea);
    }

    public void clickLogIn(MouseEvent event) throws IOException {

        NPBPRouter.goTo("home");
    }

}
