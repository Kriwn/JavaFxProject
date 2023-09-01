package cs211.project.controllers;

import cs211.project.models.Chat;
import cs211.project.models.ChatText;
import cs211.project.services.ChatDatasource;
import cs211.project.services.Datasource;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatController {
    @FXML private AnchorPane page;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vbox;
    @FXML private TextField textField;
    private Chat chat;
    private ArrayList<ChatText> texts;
    private Datasource<Chat> datasource;

    public void initialize(){
        scrollPane.setVvalue(1.0);
        datasource = new ChatDatasource("chat", "chat.csv");
        chat = datasource.readData();
        texts = chat.getChat();
        datasource.writeData(chat);

        for(var text : texts){
            vbox.getChildren().add(createCard(text));
        }

        textField.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            if(event.getCode()== KeyCode.ENTER){
                send();
            }
        });
    }

    public HBox createCard(ChatText text){
        File file = new File("src/main/resources/cs211/project/views/chat-text.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        HBox hbox = null;
        try {
            hbox = (HBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ChatTextController chatTextController = (ChatTextController) fxmlLoader.getController();
        Label usernameLabel = chatTextController.getUsernameLabel();
        Label dateTimeLabel = chatTextController.getDateTimeLabel();
        Label textLabel = chatTextController.getTextLabel();

        usernameLabel.setText(text.getUsername());
        dateTimeLabel.setText(text.getTime().toString());
        textLabel.setText(text.getText());

        return hbox;
    }

    public void send(){
        if(textField.getText().equals("")){return;}

        LocalDateTime now = LocalDateTime.now();
        String time = ""+now.getYear()+"-"+"%02d".formatted(now.getMonthValue())+"-"+"%02d".formatted(now.getDayOfMonth())+"T"+"%02d".formatted(now.getHour())+":"+ "%02d".formatted(now.getMinute())+":"+"%02d".formatted(now.getSecond());
        ChatText text = new ChatText(LocalDateTime.parse(time),"USER",textField.getText());
        HBox hbox = createCard(text);
        vbox.getChildren().add(hbox);

        texts.add(text);
        datasource.writeData(chat);

        textField.clear();

        try {
            NPBPRouter.loadPage("chat",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToMyCreateEvent(){
        try {
            NPBPRouter.loadPage("my-create-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
