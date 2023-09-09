package cs211.project.services;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class NPBPAnimation {
    public static void pageTransition(Object object, int duration){
        if(object instanceof AnchorPane) object = (AnchorPane) object;
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), (Node) object);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
