package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.services.AccountDatasource;
import cs211.project.services.Datasource;
import cs211.project.services.EventDatasource;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyEventController implements Initializable {
    private Datasource<AccountList> datasourceAccount;
    private AccountList users;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datasourceAccount = new AccountDatasource("data","account.csv");
        String username = "Taro";
        users = datasourceAccount.readData();
        user = (User)users.findUserByUsername(username);
    }
}
