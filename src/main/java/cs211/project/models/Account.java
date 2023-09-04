package cs211.project.models;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

public class Account {
    private static int genId = 1;
    private int accountId;
    private String name;
    private String username;
    private String password;
    private String image;
    private String roleAccount;
    private LocalDateTime timeLogin;

    /**
     * Use this constructor when sign up
     * and use [account object].hashPassword(plain)
     * @param username
     * @param name
     */
    public Account(String username, String name) {
        this.name = name;
        this.accountId = genId;
        genId++;
        this.username = username;
        this.password = null;
        this.roleAccount = "User";
        this.image = "images/default.png";
        this.timeLogin = LocalDateTime.now();
    }
    public Account(String username, String name,String Id, String role, String image, String time) {
        genId = Integer.parseInt(Id)+1;
        this.name = name;
        this.username = username;
        this.accountId = Integer.parseInt(Id);
        this.password = null;
        this.roleAccount = role;
        this.image = image;
        this.timeLogin = LocalDateTime.parse(time);
    }


    /**
     * Use this constructor when reading data from file
     * @param username
     * @param name
     * @param password
     */
    public Account(String username,String name,String Id,String password,String role, String image,String time){
        this(username, name,Id,role,image,time);
        this.password = password;
    }

    public boolean isUsername(String username){
        return this.username.equals(username);
    }


    public void hashPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
    public  static boolean confirmPassword(String password,String confirmPassword){
        if(password.equals(confirmPassword)){
            return true;
        }
        return false;
    }
    public boolean validatePassword(String password) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), this.password);
        return result.verified;
    }
    public boolean validatePassword2(String password){
        return this.password.equals(password);
    }

    public String getPassword() {
        return password;
    }
    public void setImage(String image){
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }

    public String getRoleAccount() {
        return roleAccount;
    }

    public LocalDateTime getTimeLogin() {
        return timeLogin;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setTimeLogin(LocalDateTime timeLogin) {
        this.timeLogin = timeLogin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", roleAccount='" + roleAccount + '\'' +
                ", timeLogin=" + timeLogin +
                '}';
    }
}
