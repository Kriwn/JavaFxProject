package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.AccountList;
import cs211.project.services.AccountDatasource;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable{
    @FXML AnchorPane signUpArea;
    @FXML TextField userNameLabel;
    @FXML TextField nameLabel;
    @FXML PasswordField passwordLabel;
    @FXML PasswordField confirmPasswordLabel;
    @FXML Label errorLabel;
    private AccountList accounts;

    public void initialize(URL location, ResourceBundle resources){
        AccountDatasource datasource = new AccountDatasource("data","account.csv");
        accounts = datasource.readData();
    }
    public void onButtonRegister() {
        String name = nameLabel.getText();
        String username = userNameLabel.getText();
        String password = passwordLabel.getText();
        String confirmPassWord = confirmPasswordLabel.getText();
        if (accounts.checkUserByUsername(username)) {
            if (Account.confirmPassword(password, confirmPassWord)) {
                accounts.signUp(name, username, password);
                errorLabel.setText("success");
                try {
                    NPBPRouter.loadPage("login",signUpArea);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                errorLabel.setText("Please make sure your passwords match.");
            }
        }
        else{
            errorLabel.setText("This username is already use.");
        }
    }
    public void clickBack(MouseEvent event) throws IOException {
        NPBPRouter.loadPage("login", signUpArea);
    }

    @FXML public void changeTheme() {
        String css = getClass().getResource("css/theme-1.css").toExternalForm();
        Scene scene = signUpArea.getScene();
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(css);
    }
}
