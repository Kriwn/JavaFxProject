package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AdminPassControllers {

    private User user;
    @FXML private TextField oldTextField;
    @FXML private TextField newTextField;
    @FXML private TextField conTextField;
    @FXML private Label errorLabel;
    private AccountList accounts;
    private AccountRepository repository;

    public void initialize(){
        repository = new AccountRepository();
        accounts = repository.getAccounts();
        user = (User) NPBPRouter.getDataAccount();
        errorLabel.setText("");
    }
    public void confirm(){
        String oldPass = oldTextField.getText().trim();
        String newPass = newTextField.getText().trim();
        String conPass = conTextField.getText().trim();
        if (oldPass.equals("") || newPass.equals("") || conPass.equals("")) {
            errorLabel.setText("Please fill  is required");
            return ;
        }
        if (user.validatePassword(oldPass)) {
            if (newPass.equals(conPass)) {
                accounts.changePassword(user.getUsername(),newPass);
                repository.save(accounts);
                System.out.println("Change password successfully");
            }
            else
                errorLabel.setText("Not matching password and confirm password");
        }
        else
        {
            errorLabel.setText("Please fill the correct old password");
            return ;
        }
    }
}
