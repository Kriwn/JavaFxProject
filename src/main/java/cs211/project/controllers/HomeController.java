package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    @FXML
    public  void onButtonGoToLoginPage(){
        try {
            FXRouter.goTo("LoginPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public  void onButtonGoToCreateEventPage(){
        try {
            FXRouter.goTo("createEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public  void onButtonGoToJoinEventPage(){
        try {
            FXRouter.goTo("LoginPage");
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
    public void onButtonGoToSetting() {
        try {
            FXRouter.goTo("Setting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
