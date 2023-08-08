package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;


public class AdminPassControllers {
    @FXML
    public void onButtonGoToOnAdminPass() {
        try {
            FXRouter.goTo("AdminPass");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onButtonGoToOnAdminMain() {
        try {
            FXRouter.goTo("AdminMain");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public  void onButtonGoToLoginPage(){
        try {
            FXRouter.goTo("LoginPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
