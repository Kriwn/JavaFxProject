package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.repository.AccountRepository;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;


public class AdminMainController implements Initializable {
    @FXML MFXTableView mfxTableView;
    @FXML Label nameLabel;
    @FXML Label userNameLabel;
    @FXML Label timeLabel;
    @FXML Circle image;
    private AccountList accountList;
    private AccountRepository accountRepository;
    private ArrayList<Account> accountArrayList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountRepository = new AccountRepository();
        accountList = accountRepository.getAccounts();
        accountArrayList = accountList.getAccounts();
        setTable(accountArrayList, mfxTableView);
    }
    private void setTable(ArrayList<Account> accounts, MFXTableView<Account> tableView) {
        MFXTableColumn<Account> nameColumn = new MFXTableColumn<>("Name", Comparator.comparing(Account::getName));
        MFXTableColumn<Account> userNameColumn = new MFXTableColumn<>("Username", Comparator.comparing(Account::getUsername));
        MFXTableColumn<Account> date = new MFXTableColumn<>("Date", Comparator.comparing(Account::getTimeLogin));

        nameColumn.setRowCellFactory(account -> new MFXTableRowCell<>(Account::getName));
        userNameColumn.setRowCellFactory(account -> new MFXTableRowCell<>(Account::getUsername));
        date.setRowCellFactory(account -> new MFXTableRowCell<>(Account::getTimeLogin));
        tableView.getTableColumns().clear();
        tableView.getTableColumns().addAll(userNameColumn , nameColumn , date);

        for(Account account: accounts){
            tableView.getItems().add(account);
        }
        tableView.getSelectionModel().selectionProperty().addListener((observableValue, Old, New) -> {
            if(New != null){
                setData(tableView.getSelectionModel().getSelectedValue());
            }
        });
    }
    private void setData(Account account){
        nameLabel.setText(account.getName());
        userNameLabel.setText(account.getUsername());
        timeLabel.setText(account.getTimeLogin().format(DateTimeFormatter.ofPattern("dd.MM.YYYY : HH.mm a")));
        image.setFill(new ImagePattern(new Image("file:" + account.getImagePath())));
    }
}


