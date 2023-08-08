package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class MyEventController {
    @FXML
    public  void onButtonGoToLoginPage(){
        try {
            FXRouter.goTo("LoginPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onButtonGoToHome() {
        try {
            FXRouter.goTo("Home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onButtonGoToControlUserEvent() {
        try {
            FXRouter.goTo("ControlUserEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public  void onButtonGoToMyEvent(){
        try {
            FXRouter.goTo("MyEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public  void onButtonGoToSetting(){
        try {
            FXRouter.goTo("Setting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goToEditEvent() {
        try {
            FXRouter.goTo("edit-event");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
