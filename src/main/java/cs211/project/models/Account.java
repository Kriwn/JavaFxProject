package cs211.project.models;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

public class Account {
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
        this.username = username;
        this.password = null;
        this.roleAccount = "NormalUser";
    }

    /**
     * Use this constructor when reading data from file
     * @param username
     * @param name
     * @param password
     */
    public Account(String username,String name,String password){
        this(username, name);
        this.password = password;
    }
    public boolean isUsername(String username){
        return this.username.equals(username);
    }


    public void hashPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
    public boolean confirmPassword(String password,String confirmPassword){
        if(password.equals( confirmPassword)){
            return true;
        }
        return false;
    }
    public boolean validatePassword(String password){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),this.password);
        return  result.verified;
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
