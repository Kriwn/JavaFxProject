package cs211.project.models;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ChatText {
    private LocalDateTime time;
    private String username;
    private String text;

    public ChatText(LocalDateTime time, String username, String text){
        this.time = time;
        this.username = username;
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUsername(String username){
        return this.username.equals(username);
    }
}
