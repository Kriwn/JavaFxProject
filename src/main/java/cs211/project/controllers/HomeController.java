package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable{
    @FXML AnchorPane contentArea;
    public void initialize(URL location, ResourceBundle resources){
        try {
            NPBPRouter.loadPage("home-page",contentArea);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
