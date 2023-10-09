package cs211.project.repository;


import cs211.project.pivot.AccountEventList;
import cs211.project.services.AccountEventJoinDatasource;
import cs211.project.services.AccountEventOwnerDatasource;
import cs211.project.services.Datasource;

public class AccountEventRepository {
    private AccountEventList listJoin;
    private AccountEventList listCreate;
    private Datasource<AccountEventList> joinEventData;
    private Datasource<AccountEventList> ownerEventData;

    public AccountEventRepository (){
        joinEventData = new AccountEventJoinDatasource("data","account_join_events.csv");
        ownerEventData = new AccountEventOwnerDatasource("data","account_owner_events.csv");
        listJoin = joinEventData.readData();
        listCreate = ownerEventData.readData();
    }
    public void saveEventJoin( AccountEventList eventList_join){
        joinEventData.writeData(eventList_join);
    }
    public void saveEventOwner(AccountEventList eventList_create){
        ownerEventData.writeData(eventList_create);
    }

    public AccountEventList getListJoin() {
        return listJoin;
    }

    public AccountEventList getListCreate() {
        return listCreate;
    }
}
