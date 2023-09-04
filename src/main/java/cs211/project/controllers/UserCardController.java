package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class UserCardController {
    @FXML
    private Circle imgCircle;
    @FXML
    private Label usernameLabel;

    public Circle getImgCircle() {
        return imgCircle;
    }

    public void setImgCircle(Circle imgCircle) {
        this.imgCircle = imgCircle;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }
}
