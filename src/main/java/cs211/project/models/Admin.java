package cs211.project.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Admin extends Account{
    public Admin(String username, String name) {
        super(username, name);
    }
    public Admin(String username, String name,String Id, String password, String role, String image, String time, String theme) {
        super(username, name,Id, password, role, image, time, theme);
    }
    public static void sort(ArrayList<Account> accountArrayList, Comparator<Account> cmp){
        Collections.sort(accountArrayList, cmp);
    }
    @Override
    public String toString() {
        return "Admin{" + "Username = "+
                getUsername() + ", name = " + getName() + ", password = " + getPassword() + ", image = " +
                getImagePath() + ", role = " + getRoleAccount() + ", timeLogin = "+ getTimeLogin() +
                '}';
    }
}
