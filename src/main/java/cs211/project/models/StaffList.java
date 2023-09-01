package cs211.project.models;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class StaffList {
    private ArrayList<Staff> staffs;
    public StaffList(){staffs = new ArrayList<>();}
    public void addStaff(String username, String role, String status, Circle circle, String imagePath){
        staffs.add(new Staff(username, role, status, circle, imagePath));
    }

    public Staff findStaffByUsername(String username){
        for(Staff urs : staffs){
            if(urs.isUsername(username)){
                return urs;
            }
        }
        return null;
    }

    public ArrayList<Staff> getStaffs() {
        return staffs;
    }
}
