package cs211.project.models;

import java.util.ArrayList;

public class TeamList {
    private ArrayList<Team> teams;
    public TeamList(){
        teams = new ArrayList<>();
    }
    public void addNewTeam(String name, int capacity, String openDate, String openTime, String closeDate, String closeTime, StaffList staffs){
        teams.add(new Team(name, capacity, openDate, openTime, closeDate, closeTime, staffs));
    }
    public Team findTeamByName(String name){
        for(Team team : teams){
            if(team.isTeamName(name)){
                return team;
            }
        }
        return null;
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
}
