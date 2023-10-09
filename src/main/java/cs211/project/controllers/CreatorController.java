package cs211.project.controllers;

import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class CreatorController {
    @FXML private Rectangle bossRec;
    @FXML private Rectangle nonRec;
    @FXML private Rectangle palmRec;
    @FXML private Rectangle peakRec;
    public void initialize(){
        bossRec.setFill(new ImagePattern(new Image("file:"+"images/Creator/Boss.jpg")));
        nonRec.setFill(new ImagePattern(new Image("file:"+"images/Creator/Non.jpg")));
        palmRec.setFill(new ImagePattern(new Image("file:"+"images/Creator/Palm.jpg")));
        peakRec.setFill(new ImagePattern(new Image("file:"+"images/Creator/Peak.png")));
    }
    public void backToApp(){
        try {
            NPBPRouter.goTo("app");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
