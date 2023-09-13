package cs211.project.controllers;

import cs211.project.models.ChatText;
import cs211.project.models.Team;
import cs211.project.repository.TeamRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TeamListController {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vBox;
    @FXML private AnchorPane page;
    private Team selectedTeam;
    private TeamRepository teamRepository;

    public void initialize(){

    }

    public HBox createCard(Team team){
        File file = new File("src/main/resources/cs211/project/views/team-card.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        HBox hbox = null;
        try {
            hbox = (HBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TeamCardController teamCardController = (TeamCardController) fxmlLoader.getController();
        Label teamNameLabel = teamCardController.getTeamNameLabel();
        Label countMemberLabel = teamCardController.getCountMemberLabel();
        Label maxMemberLabel = teamCardController.getMaxMemberLabel();

        teamNameLabel.setText(team.getTeamName());
        countMemberLabel.setText(""+team.getCountMember());
        maxMemberLabel.setText(""+team.getMaxMember());

        return hbox;
    }
}
