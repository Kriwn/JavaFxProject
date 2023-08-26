package cs211.project.models;

public class NormalUser extends Account{

    public NormalUser(String username, String name) {
        super(username, name);
    }


    public NormalUser(String username, String name, String password, String role, String image, String time) {
        super(username,name,password,role,image,time);
    }
}
