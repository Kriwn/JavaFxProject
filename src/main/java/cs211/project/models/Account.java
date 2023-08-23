package cs211.project.models;

public class Account {
    private String name;
    private String username;
    protected String password;
    private String image;

    public Account(String username,String name,String password){
        this.name = name;
        this.username = username;
        this.password = password;
    };

    public void setPassword(String password) {
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }
}
