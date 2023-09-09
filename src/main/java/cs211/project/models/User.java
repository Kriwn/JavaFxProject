package cs211.project.models;

import java.util.ArrayList;

public class User extends Account{
    private ArrayList<Integer> myEvents;
    private ArrayList<Integer> myCreateEvents;

    public User(String username, String name) {
        super(username, name);
        myEvents = new ArrayList<>();
        myCreateEvents = new ArrayList<>();
    }

    public User(String username, String name,String Id, String password, String role, String image, String time) {
        super(username,name,Id,password,role,image,time);
        myEvents = new ArrayList<>();
        myCreateEvents = new ArrayList<>();
    }
    public void addMyCreateEventFromFile(ArrayList<Integer> myCreateEvents){
        if(myCreateEvents != null){
            this.myCreateEvents = myCreateEvents;
        }
    }
    public void addMyEventFromFile(ArrayList<Integer> myEvents){
        if(myEvents != null){
            this.myEvents = myEvents;
        }
    }



    public ArrayList<Integer> getMyEvents(){return myEvents;}
    public ArrayList<Integer> getMyCreateEvents(){
        return myCreateEvents;
    }

    public void addMyCreateEvent(int id) {
        myCreateEvents.add(id);
    }
    public void addMyEvent(int id){
        myEvents.add(id);
    }

    @Override
    public String toString() {
        return "User{" + "Username = "+
                getUsername() + ", name = " + getName() + ", password = " + getPassword() + ", image = " +
                getImage() + ", role = " + getRoleAccount() + ", timeLogin = "+ getTimeLogin() +
                ", myEvents=" + myEvents +
                ", myCreateEvents=" + myCreateEvents +
                '}';
    }
}
