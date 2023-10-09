package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChatTextController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label textLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label usernameLabel;

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public void setTextLabel(Label textLabel) {
        this.textLabel = textLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }
}
