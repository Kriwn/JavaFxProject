package cs211.project.models;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;
    private int lastId = 0;

    public AccountList(){
        accounts = new ArrayList<>();
    }
    public Account findUserByUsername(String username){
        for(Account account: accounts){
            if(account.isUsername(username)){
                return account;
            }
        }
        return null;
    }
    public Boolean checkUserByUsername(String username){
        for(Account account: accounts){
            if(account.isUsername(username)){
                return false;
            }
        }
        return true;
    }
    /*เพิ่มuser จากการอ่านไฟล์*/
    public void addNewUserFromFile(String username, String name,String id, String password,String role,String image,String time){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        Account exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                accounts.add(new User(username,name,id,password,role,image,time));
                this.lastId = Integer.parseInt(id);
            }
        }
    }
    public void addNewAdminFromFile(String username, String name,String Id, String password, String role, String image, String time){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        Account exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                accounts.add(new Admin(username,name,Id,password,role,image,time));
            }
        }
    }

    public void signUp(String username, String name, String password){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        Account exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                User user = new User(username, name);
                user.setAccountId(++lastId);
                user.hashPassword(password);
                accounts.add(user);
            }
        }
    }
    public void addUser(Account account) {
        accounts.add((User)account);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword){
        Account exist = findUserByUsername(username);

        return false;
    }
    public Account login(String username, String password){
        Account account = findUserByUsername(username);
        boolean flag = account.validatePassword(password);
        if(flag){
            return account;
        }
        return null;
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }
}
