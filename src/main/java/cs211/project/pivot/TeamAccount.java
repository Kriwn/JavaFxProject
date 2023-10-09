package cs211.project.pivot;

public class TeamAccount {
    private int account_id;
    private int team_id;
    private String status = "NotBan";
    private String role = "Member";

    public TeamAccount(int acc_id, int team_id){
        this.account_id = acc_id;
        this.team_id = team_id;
    }
    public TeamAccount(int acc_id, int team_id, String status, String role){
        this.account_id = acc_id;
        this.team_id = team_id;
        this.status = status;
        this.role = role;
    }

    public boolean isAccountId(int id){
        return this.account_id == id;
    }
    public boolean isTeamId(int id){
        return this.team_id == id;
    }

    public int getAccount_id() {
        return account_id;
    }
    public int getTeam_id() {
        return team_id;
    }
    public String getStatus(){
        return status;
    }
    public String getRole() {
        return role;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "TeamAccount{" +
                "account_id=" + account_id +
                ", team_id=" + team_id +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
