package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.repository.TeamRepository;
import cs211.project.services.NPBPRouter;
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
import java.util.ArrayList;

public class TeamListController {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vbox;
    @FXML private AnchorPane page;
    private Team selectedTeam;
    private TeamRepository teamRepository;
    private EventRepository eventRepository;
    private AccountRepository accountRepository;
    private User user;
    private Event event;
    private TeamList teamlist;
    private ArrayList<Team> teams;

    public void initialize(){
        user = (User) NPBPRouter.getDataAccount();
        event = (Event) NPBPRouter.getDataEvent();
        eventRepository = new EventRepository();
        accountRepository = new AccountRepository();
        teamRepository = new TeamRepository();
        teamlist = teamRepository.getTeamList();
        teams = teamlist.getTeams();

        for(var team : teams){
            vbox.getChildren().add(createCard(team));
        }
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

        hbox.setOnMouseClicked(click ->{
            try {
                NPBPRouter.loadPage("staff-list",page,user,event,team);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return hbox;
    }

    public void backButton(){
        try {
            NPBPRouter.loadPage("my-create-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTeamButton(){
        try {
            NPBPRouter.loadPage("create-staff-team",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
