package cs211.project.models;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Staff {
    private String username;
    private String role;
    private String status;
    private Circle img;
    private String imagePath;

    public Staff(String username){
        this.username = username;
        role = "Member";
        status = "Normal";
        img = new Circle(100);
        imagePath = "src/main/resources/cs211/project/Images/add-friend.png";
        img.setFill(new ImagePattern(new Image("file:" + imagePath)));
    }

    public Staff(String username, String role, String status, Circle circle, String imagePath){
        this.username = username;
        this.role = role;
        this.status = status;
        this.img = circle;
        this.imagePath = imagePath;
        img.setFill(new ImagePattern(new Image("file:" + imagePath)));
    }

    public String getUsername() { return username; }

    public String getRole() { return role; }

    public String getStatus() { return status; }

    public Circle getImg() { return img; }

    public String getImagePath() {
        return imagePath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImg(Circle img) {
        this.img = img;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isUsername(String username){
        return this.username.equals(username);
    }

}
