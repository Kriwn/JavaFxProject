package cs211.project.controllers;

import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MyCreateEventController {
    @FXML
    AnchorPane page;
    public void goToStaffList(){
        try {
            NPBPRouter.loadPage("staff-list",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goToChat(){
        try {
            NPBPRouter.loadPage("chat",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
