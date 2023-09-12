package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.AccountList;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPRouter;
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
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable{
    @FXML  AnchorPane signUpArea;
    @FXML  MFXTextField userNameField;
    @FXML  MFXTextField nameField;
    @FXML  MFXPasswordField passwordField;
    @FXML  MFXPasswordField confirmPasswordField;
    @FXML  Label errorLabel;
    private AccountList accounts;
    private AccountRepository repository;

    public void initialize(URL location, ResourceBundle resources){
        repository = new AccountRepository();
        accounts = repository.getAccounts();
        confirmPasswordField.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
            if (click.getCode() == KeyCode.ENTER) {
                onButtonRegister();
            }
        });
    }
    public void onButtonRegister() {
        String name = nameField.getText();
        String username = userNameField.getText();
        String password = passwordField.getText();
        String confirmPassWord = confirmPasswordField.getText();
        if (accounts.checkUserByUsername(username)) {
            if (Account.confirmPassword(password, confirmPassWord)) {
                accounts.signUp(username, name, password);
                errorLabel.setText("success");
                repository.save(accounts);
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
