package cs211.project.models;

import java.util.ArrayList;

public class ActivityList {
    private ArrayList<Activity> activities;
    private  int lastId = 0;

    public  ActivityList(){activities = new ArrayList<>();}

    public void  addNewActivityFromFile(String name,String detail,String id){
        name = name.trim();
        detail = detail.trim();
        id = id.trim();
        if (!name.equals("") & !detail.equals("")){
            Activity exist = findActivityByName(name);
            if (exist == null){
                activities.add(new Activity(name,detail,Integer.parseInt(id)));
                this.lastId = Integer.parseInt(id);
            }
        }
    }

    public void  addNewActivity(String name,String detail){
        name = name.trim();
        detail = detail.trim();
        if (!name.equals("") & !detail.equals("")){
            Activity exist = findActivityByName(name);
            if (exist == null){
                activities.add(new Activity(name,detail,++lastId));
            }
        }
    }

    public Activity findActivityByName(String name){
        for (Activity activity : activities) {
            if (activity.isName(name)){
                return activity;
            }
        }
        return null;
    }


    public  ArrayList<Activity> getActivity(){
        return activities;
    }
}
