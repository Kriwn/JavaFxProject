package cs211.project.cs211661project;

import javafx.application.Application;
import javafx.stage.Stage;
import cs211.project.services.FXRouter;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXRouter.bind(this, stage, "CS211 661 Project",1024,760);
        configRoute();
        FXRouter.goTo("LoginPage");
    }

    private static void configRoute() {
        String resourcesPath = "cs211/project/views/";
        FXRouter.when("hello", resourcesPath + "hello-view.fxml");
        FXRouter.when("LoginPage", resourcesPath + "LoginPage.fxml");
        FXRouter.when("ControlUserEvent",resourcesPath + "ControlUserEvent.fxml");
        FXRouter.when("AdminPass",resourcesPath + "Admin-pass.fxml");
        FXRouter.when("Home",resourcesPath + "Home.fxml");
        FXRouter.when("SingUp",resourcesPath + "SingUp.fxml");
    }


    public static void main(String[] args) {
        launch();
    }
}