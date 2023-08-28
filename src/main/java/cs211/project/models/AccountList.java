package cs211.project.models;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<User> users;
    private ArrayList<Admin> admins;

    public AccountList(){
        users = new ArrayList<>();
        admins = new ArrayList<>();
    }
    public User findUserByUsername(String username){
        for(User user: users){
            if(user.isUsername(username)){
                return user;
            }
        }
        return null;
    }
    public Boolean checkUserByUsername(String username){
        for(User user: users){
            if(user.isUsername(username)){
                return false;
            }
        }
        return true;
    }
    /*เพิ่มuser จากการอ่านไฟล์*/
    public void addNewUserFromFile(String username, String name, String password,String role,String image,String time){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        User exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                users.add(new User(username,name,password,role,image,time));
            }
        }
    }
    public void addNewAdminFromFile(String username, String name, String password, String role, String image, String time){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        User exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                admins.add(new Admin(username,name,password,role,image,time));
            }
        }
    }

    public void signUp(String username, String name, String password){
        username = username.trim();
        name = name.trim();
        password = password.trim();
        User exist = findUserByUsername(username);
        if(!username.equals("") && !name.equals("") && !password.equals("")){
            if(exist == null){
                User user = new User(username, name);
                user.hashPassword(password);
                users.add(user);
            }
        }
    }
    public boolean changePassword(String username, String oldPassword, String newPassword){
        User exist = findUserByUsername(username);

        return false;
    }

    public User login(String username, String password){
        User user = findUserByUsername(username);
        boolean flag = user.validatePassword(password);
        if(flag){
            return user;
        }
        return null;
    }
    public ArrayList<User> getUsers(){
        return users;
    }

    public ArrayList<Admin> getAdmins(){
        return admins;
    }
}
