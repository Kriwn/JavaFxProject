package cs211.project.models;

import java.util.ArrayList;

public class Admin extends Account{
    public ArrayList<NormalUser> users;
    public Admin(String username, String name, String password) {
        super(username, name, password);
    }

}
