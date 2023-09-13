package cs211.project.models;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class TeamList {
    private ArrayList<Team> teams;
    private int lastId;
    public TeamList(){
        teams = new ArrayList<>();
    }
    public void addNewTeam(String team_id, String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime, StaffList staffs){
        team_id = team_id.trim();
        name = name.trim();
        maxMember = maxMember.trim();
        openDate = openTime.trim();
        openTime = openTime.trim();
        closeDate = closeDate.trim();
        closeTime = closeTime.trim();

        if (!name.equals("")) {
            Team exist = findTeamByName(name);
            if (exist == null) {
                teams.add(new Team(team_id, name, maxMember, openDate, openTime, closeDate, closeTime, staffs));
                this.lastId = Integer.parseInt(team_id);
            }
        }
    }

    public void createTeam(String team_id, String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime, StaffList staffs){
        team_id = team_id.trim();
        name = name.trim();
        maxMember = maxMember.trim();
        openDate = openTime.trim();
        openTime = openTime.trim();
        closeDate = closeDate.trim();
        closeTime = closeTime.trim();

        Team exist = findTeamByName(name);
        if(!name.equals("")){
            if(exist == null){
                teams.add(new Team(""+(++lastId), name, maxMember, openDate, openTime, closeDate, closeTime, staffs));
            }
        }
    }

    public Team findTeamByName(String name){
        for(Team team : teams){
            if(team.isTeamName(name)){
                return team;
            }
        }
        return null;
    }

    public Team findTeamById(int team_id){
        for(Team team : teams){
            if(team.isTeamId(team_id)){
                return team;
            }
        }
        return null;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
}
