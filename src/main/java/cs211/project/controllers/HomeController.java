package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable{
    @FXML AnchorPane page;
    public void initialize(URL location, ResourceBundle resources){
        try {
            NPBPRouter.loadPage("home-page",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onHomeButton(){
        try {
            NPBPRouter.loadPage("home-page",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onMyEventButton(){
        try {
            NPBPRouter.loadPage("my-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onMyCreateEventButton(){
        try {
            NPBPRouter.loadPage("my-create-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onSettingButton(){
        try {
            NPBPRouter.loadPage("setting",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onLogout(){
        try {
            NPBPRouter.goTo("app");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
