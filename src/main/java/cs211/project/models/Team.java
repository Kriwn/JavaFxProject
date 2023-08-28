package cs211.project.models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private StaffList staffs;
    private int capacity;
    private String openDate;
    private String openTime;
    private String closeDate;
    private String closeTime;

    public Team(String name, int capacity, String openDate, String openTime, String closeDate, String closeTime, StaffList staffs){
        this.teamName = name;
        this.capacity = capacity;
        this.openDate = openDate;
        this.openTime = openTime;
        this.closeDate = closeDate;
        this.closeTime = closeTime;
        this.staffs = staffs;
    }

    public String getTeamName() {return teamName;}

    public int getCapacity() {return capacity;}

    public String getOpenDate() {return openDate;}

    public String getOpenTime() {return openTime;}

    public String getCloseDate() {return closeDate;}

    public String getCloseTime() {return closeTime;}

    public StaffList getStaffs() {return staffs;}

    public boolean isTeamName(String name) {
        return this.teamName.equals(name);
    }


}
