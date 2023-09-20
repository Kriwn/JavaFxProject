package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Team {
    private int team_id;
    private String teamName;
    private StaffList staffList;
    private int countMember;
    private int maxMember;
    private LocalDate openDate;
    private LocalTime openTime;
    private LocalDate closeDate;
    private LocalTime closeTime;

    public Team(String team_id, String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime){
        this.team_id = Integer.parseInt(team_id);
        this.teamName = name;
        this.maxMember = Integer.parseInt(maxMember);
        this.openDate = LocalDate.parse(openDate);
        this.openTime = LocalTime.parse(openTime);
        this.closeDate = LocalDate.parse(closeDate);
        this.closeTime = LocalTime.parse(closeTime);
        this.countMember = 0;
    }
    
    public Team(String team_id, String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime, String countMember){
        this.team_id = Integer.parseInt(team_id);
        this.teamName = name;
        this.maxMember = Integer.parseInt(maxMember);
        this.openDate = LocalDate.parse(openDate);
        this.openTime = LocalTime.parse(openTime);
        this.closeDate = LocalDate.parse(closeDate);
        this.closeTime = LocalTime.parse(closeTime);
        this.countMember = Integer.parseInt(countMember);
    }

    public int getTeamId() {return team_id;}

    public String getTeamName() {return teamName;}

    public int getCountMember(){return countMember;}

    public int getMaxMember() {return maxMember;}

    public LocalDate getOpenDate() {return openDate;}

    public LocalTime getOpenTime() {return openTime;}

    public LocalDate getCloseDate() {return closeDate;}

    public LocalTime getCloseTime() {return closeTime;}

    public StaffList getStaffList() {return staffList;}

    public boolean isTeamName(String name) {
        return this.teamName.equals(name);
    }

    public boolean isTeamId(int team_id){
        return this.team_id == team_id;
    }

    public void addCountMember(){
        countMember++;
    }
}
