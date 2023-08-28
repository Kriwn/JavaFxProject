package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChatTextController {
    @FXML private Label dateTimeLabel;
    @FXML private Label usernameLabel;
    @FXML private Label textLabel;

    public Label getDateTimeLabel() {
        return dateTimeLabel;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public void setDateTimeLabel(Label dateTimeLabel) {
        this.dateTimeLabel = dateTimeLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public void setTextLabel(Label textLabel) {
        this.textLabel = textLabel;
    }
}
