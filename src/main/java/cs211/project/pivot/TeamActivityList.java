package cs211.project.pivot;

import java.util.ArrayList;

public class TeamActivityList {
    private ArrayList<TeamActivity> list;
    public TeamActivityList(){
        list = new ArrayList<>();
    }

    public void addNew(int team_id, int activity_id) {
        list.add(new TeamActivity(team_id,activity_id));
    }

    public ArrayList<Integer> findEventActivityByActivityId(int activity_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamActivity teamActivity: list){
            if (teamActivity.isActivityId(activity_id)){
                result.add(teamActivity.getActivity_id());
            }
        }
        return result;
    }

    public ArrayList<Integer> findEventActivityByTeamId(int team_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamActivity teamActivity: list){
            if (teamActivity.isTeamId(team_id)){
                result.add(teamActivity.getTeam_id());
            }
        }
        return result;
    }

    public ArrayList<TeamActivity> getList() {
        return list;
    }
}
