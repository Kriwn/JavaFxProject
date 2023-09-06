package cs211.project.repository;

import cs211.project.services.AccountEventJoinDatasource;
import cs211.project.services.AccountEventOwnerDatasource;
import cs211.project.services.Datasource;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountEventRepository {
    private HashMap<Integer, ArrayList<Integer>> account_join_event;
    private HashMap<Integer, ArrayList<Integer>> account_owner_event;
    private Datasource<HashMap<Integer,ArrayList<Integer>>> join_event_data;
    private Datasource<HashMap<Integer,ArrayList<Integer>>> owner_event_data;

    public AccountEventRepository (){
        join_event_data = new AccountEventJoinDatasource("data","account_join_events.csv");
        owner_event_data = new AccountEventOwnerDatasource("data","account_owner_events.csv");
        account_join_event = join_event_data.readData();
        account_owner_event = owner_event_data.readData();
    }
    public ArrayList<Integer> getEventJoinByAccountId(int id){
        return account_join_event.get(id);
    }

    public ArrayList<Integer> getEventOwnerByAccountId(int id){
        return account_owner_event.get(id);
    }
    public void saveEventJoin( HashMap<Integer, ArrayList<Integer>> event){
        join_event_data.writeData(event);
    }
    public void saveEventOwner(HashMap<Integer, ArrayList<Integer>> event){
        owner_event_data.writeData(event);
    }

    public HashMap<Integer, ArrayList<Integer>> getAccount_join_event() {
        return account_join_event;
    }

    public HashMap<Integer, ArrayList<Integer>> getAccount_owner_event() {
        return account_owner_event;
    }
}
