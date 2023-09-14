package cs211.project.pivot;

public class EventActivity {
    private int activity_id;
    private int event_id;

    public EventActivity(int activity_id,int event_id)
    {
        this.activity_id = activity_id;
        this.event_id = event_id;
    }

    public boolean isActivityId(int id){
        return this.activity_id == id;
    }
    public boolean isEventId(int id){
        return this.event_id == id;
    }

    public  int getActivity_id(){
        return  activity_id;
    }
    public int getEvent_id()
    {
        return  event_id;
    }

    @Override
    public String toString() {
        return "{EventActivity" +
                "activity_id=" + activity_id +
                ", event_id=" + event_id + '\'' +
                '}';
    }
}
