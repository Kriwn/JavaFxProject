package cs211.project.pivot;

public class TeamActivity {
    private int activity_id;
    private int team_id;

    public TeamActivity(int team_id,int activity_id){
        this.activity_id = activity_id;
        this.team_id = team_id;
    }
    public boolean isActivityId(int id){
        return this.activity_id == id;
    }
    public boolean isTeamId(int id){
        return this.team_id == id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public  int getTeam_id(){
        return  team_id;
    }

    @Override
    public String toString() {
        return "{EventActivity" +
                "activity_id=" + activity_id +
                ", team_id=" + team_id + '\'' +
                '}';
    }
}
