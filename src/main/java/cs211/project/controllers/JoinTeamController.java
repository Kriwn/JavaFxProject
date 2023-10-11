package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.TeamAccountList;
import cs211.project.repository.TeamAccountRepository;
import cs211.project.repository.TeamRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JoinTeamController implements Initializable {
    @FXML private Label countMemberLabel;
    @FXML private Label maxMemberLabel;
    @FXML private Label dateEndLabel;
    @FXML private Label dateStartLabel;
    @FXML private AnchorPane page;
    @FXML private Label teamNameLabel;
    @FXML private Label timeEndLabel;
    @FXML private Label timeStartLabel;
    private User user;
    private Team team;
    private TeamRepository teamRepository;
    private TeamList teamlist;
    private TeamAccountRepository teamAccountRepository;
    private TeamAccountList teamAccountList;

    public void initialize(URL url, ResourceBundle resourceBundle){
        user = (User) NPBPRouter.getDataAccount();

        team = (Team) NPBPRouter.getDataTeam();
        teamRepository = new TeamRepository();
        teamlist = teamRepository.getTeamList();

        teamAccountRepository = new TeamAccountRepository();

        showDetail(team);
    }

    public void showDetail(Team team){
        teamNameLabel.setText(team.getTeamName());
        dateStartLabel.setText(team.getOpenDate().toString());
        timeStartLabel.setText(team.getOpenTime().toString());
        dateEndLabel.setText(team.getCloseDate().toString());
        timeEndLabel.setText(team.getCloseTime().toString());
        countMemberLabel.setText(""+team.getCountMember());
        maxMemberLabel.setText(""+team.getMaxMember());
    }

    public void joinTeamButton(){
        teamlist.addCountMember(team.getTeamId());
        teamRepository.save(teamlist);
        teamAccountList = teamAccountRepository.getTeamAccountList();
        teamAccountList.addNew(user.getAccountId(), team.getTeamId());
        teamAccountRepository.save(teamAccountList);

        try {
            NPBPRouter.loadPage("home-page",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backButton(){
        try {
            NPBPRouter.loadPage("select-team-to-join",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
