package cs211.project.models;

import java.util.ArrayList;

public class ActivityList {
    private ArrayList<Activity> activities;
    private  int lastId = 0;

    public  ActivityList(){activities = new ArrayList<>();}

//    public void  addNewActivityFromFile(String name,String detail,String id){
//        name = name.trim();
//        detail = detail.trim();
//        id = id.trim();
//        if (!name.equals("") & !detail.equals("")){
//            Activity exist = findActivityById(Integer.parseInt(id));
//            if (exist == null){
//                activities.add(new Activity(name,detail,Integer.parseInt(id)));
//                this.lastId = Integer.parseInt(id);
//            }
//        }
//    }

//    public void  addNewActivity(String name,String detail){
//        name = name.trim();
//        detail = detail.trim();
//        if (!name.equals("") & !detail.equals("")){
//            Activity exist = findActivityByBoth(name,detail);
//            if (exist == null){
//                activities.add(new Activity(name,detail,++lastId));
//            }
//        }
//    }
//
//    public Activity findActivityById(int id){
//        for (Activity activity : activities) {
//            if (activity.isId(id)){
//                return activity;
//            }
//        }
//        return null;
//    }
//    public  Activity findActivityByBoth(String name,String detail){
//        name = name.trim();
//        detail = detail.trim();
//
//    }


    public  ArrayList<Activity> getActivity(){
        return activities;
    }
}
