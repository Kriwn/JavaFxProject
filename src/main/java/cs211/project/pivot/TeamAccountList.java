package cs211.project.pivot;

import java.util.ArrayList;

public class TeamAccountList {
    private ArrayList<TeamAccount> list;

    public TeamAccountList(){
        list = new ArrayList<>();
    }

    public void addNew(int accId, int teamId){
        list.add(new TeamAccount(accId,teamId));
    }
    public void addNew(int accId, int teamId, String status, String role){
        list.add(new TeamAccount(accId, teamId, status, role));
    }

    public ArrayList<Integer> findTeamsByAccount(int accId){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamAccount teamAccount : list){
            if(teamAccount.isAccountId(accId) && teamAccount.getStatus().equals("NotBan")){
                result.add(teamAccount.getTeamId());
            }
        }
        return result;
    }
    public ArrayList<Integer> findAccountsByTeam(int teamId){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamAccount teamAccount : list){
            if(teamAccount.isTeamId(teamId) && teamAccount.getStatus().equals("NotBan")){
                result.add(teamAccount.getAccountId());
            }
        }
        return result;
    }

    public ArrayList<Integer> findAllAccountsByTeam(int teamId){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamAccount teamAccount : list){
            if(teamAccount.isTeamId(teamId)){
                result.add(teamAccount.getAccountId());
            }
        }
        return result;
    }
    public TeamAccount findAccountInTeam(int accId, int teamId){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccountId() == accId && teamAccount.getTeamId() == teamId){
                return teamAccount;
            }
        }
        return null;
    }
    public void ban(int accId, int teamId){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccountId() == accId && teamAccount.getTeamId() == teamId){
                teamAccount.setStatus("Ban");
            }
        }
    }
    public void unBan(int accId, int teamId){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccountId() == accId && teamAccount.getTeamId() == teamId){
                teamAccount.setStatus("NotBan");
            }
        }
    }
    public void promote(int accId, int teamId){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccountId() == accId && teamAccount.getTeamId() == teamId){
                teamAccount.setRole("Head");
            }
        }
    }
    public void demote(int accId, int teamId){
        for(TeamAccount teamAccount : list){
            if(teamAccount.getAccountId() == accId && teamAccount.getTeamId() == teamId){
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
