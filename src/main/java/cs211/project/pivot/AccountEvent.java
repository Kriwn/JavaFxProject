package cs211.project.pivot;

public class AccountEvent {
    private int account_id;
    private int event_id;
    private String status = "NotBan";

    public AccountEvent(int acc_id, int ev_id){
        account_id = acc_id;
        event_id = ev_id;
    }
    public AccountEvent(int acc_id, int ev_id, String status){
        account_id = acc_id;
        event_id = ev_id;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAccountId(int id){
        return this.account_id == id;
    }
    public boolean isEventId(int id){
        return this.event_id == id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getEvent_id() {
        return event_id;
    }
    public String getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "AccountEvent{" +
                "account_id=" + account_id +
                ", event_id=" + event_id +
                ", status='" + status + '\'' +
                '}';
    }
}
