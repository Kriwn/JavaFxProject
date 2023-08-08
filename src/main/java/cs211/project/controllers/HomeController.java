package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    @FXML
    public  void onButtonGoToLoginPage(){
        try {
            FXRouter.goTo("LoginPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
