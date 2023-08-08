package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {
    @FXML
    public void onButtonGoToHome() {
        try {
            FXRouter.goTo("Home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public  void onButtonGoToSingUp(){
        try {
            FXRouter.goTo("SingUp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onButtonGoToSetting() {
        try {
            FXRouter.goTo("Setting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
