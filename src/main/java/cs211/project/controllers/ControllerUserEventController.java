package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerUserEventController {
    @FXML
    public void onButtonGoToHome() {
        try {
            FXRouter.goTo("Home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
