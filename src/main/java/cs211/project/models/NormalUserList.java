package cs211.project.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NormalUserList {
    private ArrayList<NormalUser> users;
    public NormalUserList(){
        users = new ArrayList<>();
    }
    public ArrayList<NormalUser> getUsers() {
        return users;
    }
}
