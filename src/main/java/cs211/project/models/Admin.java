package cs211.project.models;

import java.util.ArrayList;

public class Admin extends Account{
    public ArrayList<NormalUser> users;

    public Admin(String username, String name) {
        super(username, name);
    }

    public Admin(String username, String name, String role, String image, String time) {
        super(username, name, role, image, time);
    }

    public Admin(String username, String name, String password, String role, String image, String time) {
        super(username, name, password, role, image, time);
    }
}
