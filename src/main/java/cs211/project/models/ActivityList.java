package cs211.project.models;

import java.util.ArrayList;

public class ActivityList {
    private ArrayList<Activity> activities;

    public  ActivityList(){activities = new ArrayList<>();}

    public void  addNewActivity(String name,String detail){
        name = name.trim();
        detail = detail.trim();
        if (!name.equals("") & !detail.equals("")){
            Activity exist = findActivityByName(name);
            if (exist == null){

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
