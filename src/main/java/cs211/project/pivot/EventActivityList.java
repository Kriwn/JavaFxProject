package cs211.project.pivot;

import java.util.ArrayList;

public class EventActivityList {
    private ArrayList<EventActivity> list;
    public EventActivityList(){
        list = new ArrayList<>();
    }

    public void addNew(int event_id, int activity_id) {
        list.add(new EventActivity(event_id,activity_id));
    }

    public ArrayList<Integer> findEventActivityByActivityId(int activity_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(EventActivity eventActivity: list){
            if (eventActivity.isActivityId(activity_id)){
                result.add(eventActivity.getActivity_id());
            }
        }
        return result;
    }
    public ArrayList<Integer> findEventActivityByEventId(int event_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(EventActivity eventActivity: list){
            if (eventActivity.isEventId(event_id)){
                result.add(eventActivity.getEvent_id());
            }
        }
        return result;
    }

    public ArrayList<EventActivity> getList() {
        return list;
    }
}
