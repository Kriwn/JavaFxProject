package cs211.project.models;

public class NormalUser extends Account{
    public NormalUser(String username, String name, String password) {
        super(username, name, password);
    }

    public NormalUser(String username, String name) {
        super(username, name);
    }



}
