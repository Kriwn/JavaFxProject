package cs211.project.repository;


import cs211.project.pivot.AccountEventList;
import cs211.project.services.AccountEventJoinDatasource;
import cs211.project.services.AccountEventOwnerDatasource;
import cs211.project.services.Datasource;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountEventRepository {
    private AccountEventList list_join;
    private AccountEventList list_create;
    private Datasource<AccountEventList> join_event_data;
    private Datasource<AccountEventList> owner_event_data;

    public AccountEventRepository (){
        join_event_data = new AccountEventJoinDatasource("data","account_join_events.csv");
        owner_event_data = new AccountEventOwnerDatasource("data","account_owner_events.csv");
        list_join = join_event_data.readData();
        list_create = owner_event_data.readData();
    }
    public void saveEventJoin( AccountEventList eventList_join){
        join_event_data.writeData(eventList_join);
    }
    public void saveEventOwner(AccountEventList eventList_create){
        owner_event_data.writeData(eventList_create);
    }

    public AccountEventList getList_join() {
        return list_join;
    }

    public AccountEventList getList_create() {
        return list_create;
    }
}
