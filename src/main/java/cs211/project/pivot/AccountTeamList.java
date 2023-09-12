package cs211.project.pivot;

import java.util.ArrayList;

public class AccountTeamList {
    private ArrayList<AccountTeam> list;

    public AccountTeamList(){
        list = new ArrayList<>();
    }

    public void addNew(int acc_id, int team_id){
        list.add(new AccountTeam(acc_id,team_id));
    }
    public void addNew(int acc_id, int team_id, String status, String role){
        list.add(new AccountTeam(acc_id, team_id, status, role));
    }

    public ArrayList<Integer> findTeamsByAccount(int acc_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountTeam accountTeam : list){
            if(accountTeam.isAccountId(acc_id) && accountTeam.getStatus().equals("NotBan")){
                result.add(accountTeam.getTeam_id());
            }
        }
        return result;
    }
    public ArrayList<Integer> findAccountsByTeam(int team_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountTeam accountTeam : list){
            if(accountTeam.isTeamId(team_id) && accountTeam.getStatus().equals("NotBan")){
                result.add(accountTeam.getAccount_id());
            }
        }
        return result;
    }
    public ArrayList<Integer> findAllAccountsByTeam(int team_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountTeam accountTeam : list){
            if(accountTeam.isTeamId(team_id)){
                result.add(accountTeam.getAccount_id());
            }
        }
        return result;
    }
    public AccountTeam findAccountInTeam(int acc_id, int team_id){
        for(AccountTeam accountTeam : list){
            if(accountTeam.getAccount_id() == acc_id && accountTeam.getTeam_id() == team_id){
                return accountTeam;
            }
        }
        return null;
    }
    public void ban(int acc_id, int team_id){
        for(AccountTeam accountTeam : list){
            if(accountTeam.getAccount_id() == acc_id && accountTeam.getTeam_id() == team_id){
                accountTeam.setStatus("Ban");
            }
        }
    }
    public void unBan(int acc_id, int team_id){
        for(AccountTeam accountTeam : list){
            if(accountTeam.getAccount_id() == acc_id && accountTeam.getTeam_id() == team_id){
                accountTeam.setStatus("NotBan");
            }
        }
    }
    public void promote(int acc_id, int team_id){
        for(AccountTeam accountTeam : list){
            if(accountTeam.getAccount_id() == acc_id && accountTeam.getTeam_id() == team_id){
                accountTeam.setRole("Head");
            }
        }
    }
    public void demote(int acc_id, int team_id){
        for(AccountTeam accountTeam : list){
            if(accountTeam.getAccount_id() == acc_id && accountTeam.getTeam_id() == team_id){
                accountTeam.setRole("Member");
            }
        }
    }

    public ArrayList<AccountTeam> getList() {
        return list;
    }
}
