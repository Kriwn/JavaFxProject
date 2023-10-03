package cs211.project.models;

import java.util.ArrayList;

public class Admin extends Account{
    public ArrayList<User> users;

    public Admin(String username, String name) {
        super(username, name);
    }

    public Admin(String username, String name,String Id, String password, String role, String image, String time, String theme) {
        super(username, name,Id, password, role, image, time, theme);
    }

    @Override
    public String toString() {
        return "Admin{" + "Username = "+
                getUsername() + ", name = " + getName() + ", password = " + getPassword() + ", image = " +
                getImagePath() + ", role = " + getRoleAccount() + ", timeLogin = "+ getTimeLogin() +
                '}';
    }
}
