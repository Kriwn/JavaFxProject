package cs211.project.pivot;

import java.util.ArrayList;

public class TeamAccountList {
    private ArrayList<TeamAccount> list;

    public TeamAccountList(){
        list = new ArrayList<>();
    }

    public void addNew(int acc_id, int team_id){
        list.add(new TeamAccount(acc_id,team_id));
    }
    public void addNew(int acc_id, int team_id, String status, String role){
        list.add(new TeamAccount(acc_id, team_id, status, role));
    }

    public ArrayList<Integer> findTeamsByAccount(int acc_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamAccount teamAccount : list){
            if(teamAccount.isAccountId(acc_id) && teamAccount.getStatus().equals("NotBan")){
                result.add(teamAccount.getTeam_id());
            }
        }
        return result;
    }
    public ArrayList<Integer> findAccountsByTeam(int team_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamAccount teamAccount : list){
            if(teamAccount.isTeamId(team_id) && teamAccount.getStatus().equals("NotBan")){
                result.add(teamAccount.getAccount_id());
            }
        }
        return result;
    }

    public ArrayList<Integer> findAllAccountsByTeam(int team_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamAccount teamAccount : list){
            if(teamAccount.isTeamId(team_id)){
                result.add(teamAccount.getAccount_id());
            }
        }
        return result;
    }
    public TeamAccount findAccountInTeam(int acc_id, int team_id){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccount_id() == acc_id && teamAccount.getTeam_id() == team_id){
                return teamAccount;
            }
        }
        return null;
    }
    public void ban(int acc_id, int team_id){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccount_id() == acc_id && teamAccount.getTeam_id() == team_id){
                teamAccount.setStatus("Ban");
            }
        }
    }
    public void unBan(int acc_id, int team_id){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccount_id() == acc_id && teamAccount.getTeam_id() == team_id){
                teamAccount.setStatus("NotBan");
            }
        }
    }
    public void promote(int acc_id, int team_id){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccount_id() == acc_id && teamAccount.getTeam_id() == team_id){
                teamAccount.setRole("Head");
            }
        }
    }
    public void demote(int acc_id, int team_id){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccount_id() == acc_id && teamAccount.getTeam_id() == team_id){
                teamAccount.setRole("Member");
            }
        }
    }

    @Override
    public String toString() {
        return "TeamAccountList{" +
                "list=" + list +
                '}';
    }

    public ArrayList<TeamAccount> getList() {
        return list;
    }
}
