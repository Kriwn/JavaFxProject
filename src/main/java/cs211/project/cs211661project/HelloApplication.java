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
        FXRouter.goTo("edit-event");
    }

    private static void configRoute() {
        String resourcesPath = "cs211/project/views/";
        FXRouter.when("hello", resourcesPath + "hello-view.fxml");
        FXRouter.when("LoginPage", resourcesPath + "login-page.fxml");
        FXRouter.when("ControlUserEvent",resourcesPath + "control-user-event.fxml");
        FXRouter.when("Home",resourcesPath + "home.fxml");
        FXRouter.when("SingUp",resourcesPath + "sing-up.fxml");
        FXRouter.when("MyEvent",resourcesPath + "my-event.fxml");
        FXRouter.when("AdminMain",resourcesPath + "admin-main.fxml");
        FXRouter.when("AdminPass",resourcesPath + "admin-pass.fxml");
        FXRouter.when("edit-event", resourcesPath + "edit-event.fxml");
        FXRouter.when("CreateEvent",resourcesPath + "create-event.fxml");
        FXRouter.when("JoinEvent",resourcesPath + "join-event.fxml");
        FXRouter.when("Setting",resourcesPath + "setting.fxml");
        FXRouter.when("Staff",resourcesPath + "staff.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}