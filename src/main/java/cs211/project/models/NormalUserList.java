package cs211.project.models;

import java.util.ArrayList;

public class NormalUserList {
    private ArrayList<NormalUser> users;
    public NormalUserList(){
        users = new ArrayList<>();
    }
    public void addNewUser(String username, String name, String password){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        if(!username.equals("") && !name.equals("") && password.equals("")){

        }
    }
    public NormalUser findUserByUsername(String username){
       return null;
    }

    public ArrayList<NormalUser> getUsers() {
        return users;
    }
}
