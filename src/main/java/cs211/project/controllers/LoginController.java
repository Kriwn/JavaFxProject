package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.AccountList;
import cs211.project.models.Admin;
import cs211.project.models.User;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPAnimation;
import cs211.project.services.NPBPKeyPress;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML  AnchorPane loginArea; // right pane
    @FXML TextField userNameField;
    @FXML PasswordField passwordField;
    @FXML Label errorLabel;
    @FXML Button loginButton;
    private AccountList accounts;
    private AccountRepository repository;


    public void initialize(URL location, ResourceBundle resources) {
        repository = new AccountRepository();
        accounts = repository.getAccounts();
        passwordField.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
            if (click.getCode() == KeyCode.ENTER) {
                try {
                    clickLogIn();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        NPBPKeyPress.EscPress(userNameField);
    }

    public void clickSignIn() throws IOException {
        NPBPRouter.loadPage("signup", loginArea);
    }

    public void clickLogIn() throws IOException {
        String username = userNameField.getText();
        String password = passwordField.getText();
        Account exist = accounts.findUserByUsername(username);
        if(exist != null){
            if(exist.validatePassword(password)){
                if (exist instanceof User) {
                    NPBPRouter.goTo("home", exist);
                } else if (exist instanceof Admin) {

                }
            }
        }
        else{
            errorLabel.setText("wrong username or password");
        }

    }

}
