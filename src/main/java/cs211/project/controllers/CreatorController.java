package cs211.project.controllers;

import cs211.project.services.NPBPRouter;

import java.io.IOException;

public class CreatorController {
    public void backToApp(){
        try {
            NPBPRouter.goTo("app");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
