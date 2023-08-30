package cs211.project.controllers;

import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private AnchorPane page;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onCreateEventButton(){
        try {
            NPBPRouter.loadPage("create-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
