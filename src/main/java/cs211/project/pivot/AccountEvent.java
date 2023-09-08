package cs211.project.pivot;

public class AccountEvent {
    private int account_id;
    private int event_id;

    public AccountEvent(int acc_id, int ev_id){
        account_id = acc_id;
        event_id = ev_id;
    }

    public boolean isAccountId(int id){
        return false;
    }
    public boolean isEventId(int id){
        return false;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getEvent_id() {
        return event_id;
    }
}
