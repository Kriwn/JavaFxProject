package cs211.project.cs211661project;

import javafx.application.Application;
import javafx.stage.Stage;
import cs211.project.services.NPBPRouter;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        NPBPRouter.bind(this, stage, "Hi", 1024, 760);
        NPBPRouter.setCss("CSS/User/theme-1.css");
        configRoute();
//        String css = getClass().getResource("css/theme-2.css").toExternalForm();
        NPBPRouter.goTo("app");
    }
    public void configRoute(){
        String viewPath = "cs211/project/views/";
        NPBPRouter.when("app",viewPath + "app.fxml");
        NPBPRouter.when("home",viewPath + "home.fxml");
        NPBPRouter.when("login",viewPath + "login.fxml");
        NPBPRouter.when("signup",viewPath + "signup.fxml");
        NPBPRouter.when("home-page",viewPath + "home-page.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}