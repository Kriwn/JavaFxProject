package cs211.project.services;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;


public final class NPBPRouter {
    private static final String WINDOW_TITLE = "";
    private static final Double WINDOW_WIDTH = 800D;
    private static final Double WINDOW_HEIGHT = 600D;
    private static final Double FADE_ANIMATION_DURATION = 600D;
    private static NPBPRouter router;
    private static Object main;
    private static Stage window;
    private static Double windowWidth;
    private static String windowTitle;
    private static Double windowHeight;
    private static AbstractMap<String,RouteScene> routes = new HashMap();
    private static RouteScene current;
    private static String animationType;
    private static Double animationDuration;
    private static String CSS;

    private NPBPRouter(){}

    public static void bind(Object ref,Stage win){
        checkIn(ref,win);
    }
    public static void bind(Object ref,Stage win,String windowTitle){
        checkIn(ref,win);
        NPBPRouter.windowTitle = windowTitle;
    }
    public static void bind(Object ref,Stage win,double windowWidth,double windowHeight){
        checkIn(ref,win);
        NPBPRouter.windowHeight = windowHeight;
        NPBPRouter.windowWidth = windowWidth;
    }
    public static void bind(Object ref,Stage win,String windowTitle,double windowWidth,double windowHeight){
        checkIn(ref,win);
        NPBPRouter.windowHeight = windowHeight;
        NPBPRouter.windowWidth = windowWidth;
        NPBPRouter.windowTitle = windowTitle;
    }

    public static void checkIn(Object ref,Stage win){
        if(router == null){
            router = new NPBPRouter();
        }
        if(main == null){
            main = ref; // main ref to object
        }
        if(window == null){
            window = win;
        }
    }
    public static void when(String routeLabel,String scenePath){
        RouteScene routeScene = new RouteScene(scenePath);
        routes.put(routeLabel,routeScene);
    }
    public static void when(String routeLabel,String scenePath,String windowTitle){
        RouteScene routeScene = new RouteScene(scenePath,windowTitle);
        routes.put(routeLabel,routeScene);
    }
    public static void when(String routeLabel,String scenePath,Double windowHeight,Double windowWidth){
        RouteScene routeScene = new RouteScene(scenePath,windowWidth,windowHeight);
        routes.put(routeLabel,routeScene);
    }
    public static void when(String routeLabel,String scenePath,Double windowHeight,Double windowWidth,String windowTitle){
        RouteScene routeScene = new RouteScene(scenePath,windowTitle,windowWidth,windowHeight);
        routes.put(routeLabel,routeScene);
    }
    private static void routeAnimation(Parent node) {
        String anType = animationType != null ? animationType.toLowerCase() : "";
        byte var3 = -1;
        switch(anType.hashCode()) {
            case 3135100:
                if (anType.equals("fade")) {
                    var3 = 0;
                }
            default:
                switch(var3) {
                    case 0:
                        Double fd = animationDuration != null ? animationDuration : FADE_ANIMATION_DURATION;
                        FadeTransition ftCurrent = new FadeTransition(Duration.millis(fd), node);
                        ftCurrent.setFromValue(0.0D);
                        ftCurrent.setToValue(1.0D);
                        ftCurrent.play();
                    default:
                }
        }
    }
    private static void loadNewRoute(RouteScene route) throws IOException {
        current = route;
        String scenePath = "/" + route.scenePath;
        Parent resource = (Parent)FXMLLoader.load((new Object() {
        }).getClass().getResource(scenePath));
        window.setTitle(route.windowTitle);
        Scene scene = new Scene(resource,route.sceneWidth, route.sceneHeight);
        scene.getStylesheets().add((new Object(){
       }).getClass().getResource(CSS).toExternalForm());
        window.setScene(scene);
        //window.setScene(new Scene(resource,route.sceneWidth,route.sceneHeight));
        window.setResizable(false);
        window.show();
        routeAnimation(resource);
    }
    private static void loadNewPage(RouteScene route, Parent parent) throws IOException{
        Pane page = (Pane)parent;
        current = route;
        String scenePath = "/" + route.scenePath;
        Parent resource = (Parent)FXMLLoader.load((new Object() {
        }).getClass().getResource(scenePath));
        window.setTitle(route.windowTitle);
        page.getChildren().removeAll();
        page.getChildren().setAll(resource);
        page.getStylesheets().add((new Object(){}).getClass().getResource(CSS).toExternalForm());
    }
    public static void loadPage(String routeLabel, Parent parent) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewPage(route, parent);
    }
    public static void loadPage(String routeLabel, Parent parent, Object data) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.data = data;
        loadNewPage(route, parent);
    }
    public static void goTo(String routeLabel) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewRoute(route);
    }

    public static void goTo(String routeLabel, Object data) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.data = data;
        loadNewRoute(route);
    }
    public static void setCss(String path){
        String scenePath = "/cs211/project/";
        CSS = scenePath + path;
    }

    public static void startFrom(String routeLabel) throws Exception {
        goTo(routeLabel);
    }

    public static void startFrom(String routeLabel, Object data) throws Exception {
        goTo(routeLabel, data);
    }

    public static void setAnimationType(String anType) {
        animationType = anType;
    }

    public static void setAnimationType(String anType, double anDuration) {
        animationType = anType;
        animationDuration = anDuration;
    }
    public static Object getData() {
        return current.data;
    }


    public static class RouteScene{
        private String scenePath;
        private String windowTitle;
        private double sceneWidth;
        private double sceneHeight;
        private Object data;
        private RouteScene(String scenePath) {
            this(scenePath, getWindowTitle(), getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, String windowTitle) {
            this(scenePath, windowTitle, getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, double sceneWidth, double sceneHeight) {
            this(scenePath, getWindowTitle(), sceneWidth, sceneHeight);
        }

        private RouteScene(String scenePath, String windowTitle, double sceneWidth, double sceneHeight) {
            this.scenePath = scenePath;
            this.windowTitle = windowTitle;
            this.sceneWidth = sceneWidth;
            this.sceneHeight = sceneHeight;
        }

        private static String getWindowTitle() {
            return NPBPRouter.windowTitle != null ? NPBPRouter.windowTitle : "";
        }

        private static double getWindowWidth() {
            return NPBPRouter.windowWidth != null ? NPBPRouter.windowWidth : NPBPRouter.WINDOW_WIDTH;
        }

        private static double getWindowHeight() {
            return NPBPRouter.windowHeight != null ? NPBPRouter.windowHeight : NPBPRouter.WINDOW_HEIGHT;
        }
    }
}
