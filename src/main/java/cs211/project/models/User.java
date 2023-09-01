package cs211.project.models;

import java.util.ArrayList;

public class User extends Account{
    private ArrayList<Event> myEvents;
    private ArrayList<Event> myCreateEvents;

    public User(String username, String name) {
        super(username, name);
    }

    public User(String username, String name, String password, String role, String image, String time) {
        super(username,name,password,role,image,time);
    }

    public ArrayList<Event> getMyEvents(){return myEvents;}
}
