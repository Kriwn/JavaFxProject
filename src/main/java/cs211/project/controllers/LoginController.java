package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.services.AccountDatasource;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML  AnchorPane loginArea; // right pane
    @FXML TextField usernameLabel;
    @FXML PasswordField passwordLabel;
    @FXML Label errorLabel;
    private AccountList accounts;
    private AccountDatasource datasource;


    public void initialize(URL location, ResourceBundle resources){
        datasource = new AccountDatasource("data","account.csv");
        accounts = datasource.readData();

    }

    public void clickSignIn(MouseEvent event) throws IOException {
        NPBPRouter.loadPage("signup", loginArea);
    }

    public void clickLogIn(MouseEvent event) throws IOException {
        String username = usernameLabel.getText();
        String password = passwordLabel.getText();
        User exist = accounts.findUserByUsername(username);
        if(exist != null){
            if(exist.validatePassword(password)){
                NPBPRouter.goTo("home",exist.getUsername());
            }
        }
        else{
            errorLabel.setText("wrong username or password");
        }

    }

}
