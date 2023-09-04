package cs211.project.models;

import java.util.ArrayList;

public class User extends Account{
    private ArrayList<Event> myEvents;
    private ArrayList<Event> myCreateEvents;

    public User(String username, String name) {
        super(username, name);
        myEvents = new ArrayList<>();
        myCreateEvents = new ArrayList<>();
    }

    public User(String username, String name, String password, String role, String image, String time) {
        super(username,name,password,role,image,time);
        myEvents = new ArrayList<>();
        myCreateEvents = new ArrayList<>();
    }

    public ArrayList<Event> getMyEvents(){return myEvents;}
    public ArrayList<Event> getMyCreateEvents(){
        return myCreateEvents;
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
