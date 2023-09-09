package cs211.project.services;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Control;
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
    public static void scaleTransition(Control control){
        ScaleTransition scale = new ScaleTransition(Duration.millis(250), control);
        scale.setToX(1.1);
        scale.setToY(1.1);
        scale.play();
    }

    public static void reverseScale(Control control) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(250), control);
        scale.setToX(1);
        scale.setToY(1);
        scale.play();
    }
    public static void translateTransition(Control control){
        TranslateTransition translate = new TranslateTransition(Duration.millis(250), control);
        translate.setByY(-25);
        translate.setAutoReverse(true);
        translate.play();
    }
    public static void reverseTranslate(Control control){
        TranslateTransition translate = new TranslateTransition(Duration.millis(250), control);
        translate.setByY(25);
        translate.setAutoReverse(true);
        translate.play();
    }
}
