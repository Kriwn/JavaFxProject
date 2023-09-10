package cs211.project.cs211661project;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.stage.Stage;
import cs211.project.services.NPBPRouter;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class HelloApplication extends Application {
    private int cssstyle = 2;
    @Override
    public void start(Stage stage) throws IOException {
        NPBPRouter.bind(this, stage, "Hi", 1024, 760);
        NPBPRouter.setCss("CSS/User/theme-"+cssstyle+".css");
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
        NPBPRouter.when("CreateStaffTeam",viewPath + "create-staff-team.fxml");
        NPBPRouter.when("edit-event", viewPath + "edit-event.fxml");
        NPBPRouter.when("my-event",viewPath + "my-event.fxml");
        NPBPRouter.when("activity", viewPath + "activity.fxml");
        NPBPRouter.when("create-activity", viewPath + "create-activity.fxml");
        NPBPRouter.when("edit-activity", viewPath + "edit-activity.fxml");
        NPBPRouter.when("edit-user",viewPath + "edit-user.fxml");
        NPBPRouter.when("admin-main",viewPath + "admin-main.fxml");
        NPBPRouter.when("admin-pass",viewPath + "admin-pass.fxml");
        NPBPRouter.when("create-event",viewPath + "create-event.fxml");
        NPBPRouter.when("create-staff-team",viewPath + "create-staff-team.fxml");
        NPBPRouter.when("join-event",viewPath + "join-event.fxml");
        NPBPRouter.when("join-team",viewPath + "join-team.fxml");
        NPBPRouter.when("my-create-event",viewPath + "my-create-event.fxml");
        NPBPRouter.when("select-my-create-event",viewPath + "select-my-create-event.fxml");
        NPBPRouter.when("select-team",viewPath + "select-team.fxml");
        NPBPRouter.when("setting",viewPath + "setting.fxml");
        NPBPRouter.when("admin-sidebar",viewPath + "sidebar-admin.fxml");
        NPBPRouter.when("staff-card", viewPath + "staff-card.fxml");
        NPBPRouter.when("staff-list", viewPath + "staff-list.fxml");
        NPBPRouter.when("chat", viewPath + "chat.fxml");
        NPBPRouter.when("chat-text", viewPath + "chat-text.fxml");
        NPBPRouter.when("history", viewPath + "history.fxml");
        NPBPRouter.when("show-my-create-event", viewPath + "show-my-create-event.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}