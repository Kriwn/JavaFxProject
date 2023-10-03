package cs211.project.pivot;

import java.util.ArrayList;

public class EventTeamList {
    private ArrayList<EventTeam> list;
    public EventTeamList(){list = new ArrayList<>();}
    public void addNew(int eventId, int teamId){
        list.add(new EventTeam(eventId, teamId));
    }
    public ArrayList<Integer> findTeamByEvent(int eventId){
        ArrayList<Integer> result = new ArrayList<>();
        for(EventTeam eventTeam : list){
            if(eventTeam.isEventId(eventId)){
                result.add(eventTeam.getTeamId());
            }
        }
        return result;
    }

    public ArrayList<EventTeam> getList() {
        return list;
    }
}
