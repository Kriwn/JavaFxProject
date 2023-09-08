package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.services.NPBPAnimation;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable{
    @FXML AnchorPane page;
    @FXML Circle image;
    @FXML Label usernameLabel;
    private User user;
    public void initialize(URL location, ResourceBundle resources){
        user = (User)NPBPRouter.getDataAccount();
        usernameLabel.setText(user.getName());
        image.setFill(new ImagePattern(new Image("file:" + user.getImage())));
        try {
            NPBPRouter.loadPage("home-page",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene = image.getScene();
                Stage stage = (Stage) image.getScene().getWindow();
                scene.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
                    if(click.getCode() == KeyCode.ESCAPE){
                        onLogout();
                    }
                });
            }
        });
    }

    public void onHomeButton(){
        try {
            NPBPRouter.loadPage("home-page",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onMyEventButton(){
        try {
            NPBPRouter.loadPage("my-event",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onMyCreateEventButton(){
        try {
            NPBPRouter.loadPage("show-my-create-event",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onHistoryButton(){
        try {
            NPBPRouter.loadPage("history",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onSettingButton(){
        try {
            NPBPRouter.loadPage("setting",page,user);
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
