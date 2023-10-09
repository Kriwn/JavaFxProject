package cs211.project.pivot;

import java.util.ArrayList;
import java.util.Iterator;

public class TeamActivityList {
    private ArrayList<TeamActivity> list;
    public TeamActivityList(){
        list = new ArrayList<>();
    }

    public void addNew(int teamId, int activityId) {
        list.add(new TeamActivity(teamId,activityId));
    }

    public ArrayList<Integer> findEventActivityByActivityId(int activityId){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamActivity teamActivity: list){
            if (teamActivity.isActivityId(activityId)){
                result.add(teamActivity.getActivityId());
            }
        }
        return result;
    }

    public void remove(int teamId, int acId) {
        Iterator<TeamActivity> iterator = list.iterator();
        while (iterator.hasNext()) {
            TeamActivity teamActivity = iterator.next();
            if (teamActivity.isTeamId(teamId) && teamActivity.isActivityId(acId)) {
                iterator.remove(); // Remove the element using the iterator
            }
        }
    }
    public ArrayList<Integer> findEventActivityByTeamId(int teamId){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamActivity teamActivity: list){
            if (teamActivity.isTeamId(teamId)){
                result.add(teamActivity.getTeamId());
            }
        }
        return result;
    }

    public ArrayList<TeamActivity> getList() {
        return list;
    }
}
