package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.pivot.AccountEvent;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.AccountDatasource;
import cs211.project.services.Datasource;
import cs211.project.services.EventDatasource;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyEventController implements Initializable {
    private Datasource<AccountList> datasourceAccount;
    private AccountList users;
    private User user;
    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private AccountRepository accountRepository;
    private EventList eventList;
    private ArrayList<Event> events;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        accountRepository = new AccountRepository();
        eventList = eventRepository.getEvents();
        events = eventList.getEvents();
        eventRepository.save(eventList);
        accountEventRepository = new AccountEventRepository();
        ArrayList<Integer> list = new ArrayList<>();
        AccountEventList list_join = accountEventRepository.getList_join();
        ArrayList<Integer> listId = new ArrayList<>();
        listId.addAll(list_join.findEventsByAccount(user.getAccountId()));
    }
}
