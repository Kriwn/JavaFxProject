package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.AccountList;
import cs211.project.models.Admin;
import cs211.project.models.User;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPAnimation;
import cs211.project.services.NPBPRouter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML  AnchorPane loginArea; // right pane
    @FXML TextField usernameLabel;
    @FXML PasswordField passwordLabel;
    @FXML Label errorLabel;
    private AccountList accounts;
    private AccountRepository repository;


    public void initialize(URL location, ResourceBundle resources){
        repository = new AccountRepository();
        accounts = repository.getAccounts();

//        usernameLabel.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
//
//        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene = usernameLabel.getScene();
                Stage stage = (Stage) usernameLabel.getScene().getWindow();
                scene.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
                    if(click.getCode() == KeyCode.ESCAPE){
                        stage.close();
                    }
                });
            }
        });

    }

    public void clickSignIn(MouseEvent event) throws IOException {
        NPBPRouter.loadPage("signup", loginArea);
    }

    public void clickLogIn(MouseEvent event) throws IOException {
        String username = usernameLabel.getText();
        String password = passwordLabel.getText();
        Account exist = accounts.findUserByUsername(username);
        System.out.println(exist);
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
