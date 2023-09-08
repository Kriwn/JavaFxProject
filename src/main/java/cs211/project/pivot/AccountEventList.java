package cs211.project.pivot;

import java.util.ArrayList;

public class AccountEventList {
    private ArrayList<AccountEvent> list;

    public AccountEventList(){
        list = new ArrayList<>();
    }

    public void addNew(int acc_id, int ev_id){
        list.add(new AccountEvent(acc_id,ev_id));
    }
    public void addNew(int acc_id, int ev_id, String status){
        list.add(new AccountEvent(acc_id, ev_id, status));
    }

    public ArrayList<Integer> findEventsByAccount(int acc_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountEvent accountEvent: list){
            if(accountEvent.isAccountId(acc_id)){
                result.add(accountEvent.getEvent_id());
            }
        }
        return result;
    }
    public ArrayList<Integer> findAccountsByEvent(int ev_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountEvent accountEvent: list){
            if(accountEvent.isEventId(ev_id)){
                result.add(accountEvent.getAccount_id());
            }
        }
        return result;
    }

    public ArrayList<AccountEvent> getList() {
        return list;
    }
}
