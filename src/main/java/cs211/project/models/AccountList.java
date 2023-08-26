package cs211.project.models;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<NormalUser> users;

    public NormalUser findUserByUsername(String username){
        for(NormalUser user: users){
            if(user.isUsername(username)){
                return user;
            }
        }
        return null;
    }
    public Boolean checkUserByUsername(String username){
        for(NormalUser user: users){
            if(user.isUsername(username)){
                return true;
            }
        }
        return false;
    }
    /*เพิ่มuser จากการอ่านไฟล์*/
    public void addNewUserFromFile(String username, String name, String password,String role,String image,String time){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        NormalUser exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                users.add(new NormalUser(username,name,password,role,image,time));
            }
        }
    }

    public void signUp(String username, String name, String password){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        NormalUser exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                NormalUser normalUser = new NormalUser(username, name);
                normalUser.hashPassword(password);
                users.add(normalUser);
            }
        }
    }
    public boolean changePassword(String username, String oldPassword, String newPassword){
        NormalUser exist = findUserByUsername(username);

        return false;
    }

    public NormalUser login(String username,String password){
        NormalUser user = findUserByUsername(username);
        boolean flag = user.validatePassword(password);
        if(flag){
            return user;
        }
        return null;
    }

    public NormalUser getUsers() {

    }
}
