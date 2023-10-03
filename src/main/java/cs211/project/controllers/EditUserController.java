package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.AccountEvent;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {
    @FXML
    private AnchorPane page;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    private User selectUser;
    private VBox selectBox;
    private User user;
    private Event event;
    private ArrayList<Account> accounts;
    private AccountList accountList;
    private AccountRepository accountRepository;
    private AccountEventRepository accountEventRepository;
    private AccountEvent accountEvent;
    private AccountEventList list_join;
    private ArrayList<Integer> listId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User) NPBPRouter.getDataAccount();
        event = (Event) NPBPRouter.getDataEvent();

        accountRepository = new AccountRepository();
        accountEventRepository = new AccountEventRepository();

        accountList = accountRepository.getAccounts();
        list_join = accountEventRepository.getList_join();
        listId = new ArrayList<>();
        listId.addAll(list_join.findAllAccountsByEvent(event.getEventId()));
        int count=0;

        for(Integer id : listId){
            if(count%3==0){vbox1.getChildren().add(createCard(id));}
            if(count%3==1){vbox2.getChildren().add(createCard(id));}
            if(count%3==2){vbox3.getChildren().add(createCard(id));}
            count++;
        }
    }
    public VBox createCard(int id) {
        File file = new File("src/main/resources/cs211/project/views/user-card.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        VBox vbox = null;
        try {
            vbox = (VBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserCardController userCardController = (UserCardController) fxmlLoader.getController();;
        Label usernameLabel = userCardController.getUsernameLabel();
        Label statusLabel = userCardController.getStatusLabel();
        Circle img = userCardController.getImgCircle();
        accountEvent = list_join.findAccountInEvent(id, event.getEventId());

        usernameLabel.setText(accountList.findUserByAccountId(id).getName());
        statusLabel.setText(accountEvent.getStatus());
        img.setFill(new ImagePattern(new Image("file:" + accountList.findUserByAccountId(id).getImagePath())));

        VBox finalVbox = vbox;
        vbox.setOnMouseClicked(event -> {
            selectUser = (User)accountList.findUserByAccountId(id);
            selectBox = finalVbox;
        });
        return vbox;
    }
    public void banButton(){
        list_join.ban(selectUser.getAccountId(), event.getEventId());
        accountEventRepository.saveEventJoin(list_join);
        accountEvent = list_join.findAccountInEvent(selectUser.getAccountId(), event.getEventId());
        refresh(accountEvent);
    }
    public void unBanButton(){
        list_join.unBan(selectUser.getAccountId(), event.getEventId());
        accountEventRepository.saveEventJoin(list_join);
        accountEvent = list_join.findAccountInEvent(selectUser.getAccountId(), event.getEventId());
        refresh(accountEvent);
    }
    public void refresh(AccountEvent accountEvent) {
        Circle circle = (Circle) selectBox.getChildren().get(1);
        Label username = (Label) selectBox.getChildren().get(3);
        Label status = (Label) selectBox.getChildren().get(4);

        circle.setFill(new ImagePattern(new Image("file:" + user.getImagePath())));
        username.setText(selectUser.getName());
        status.setText(accountEvent.getStatus());
        selectBox.setFocusTraversable(true);
    }
}
