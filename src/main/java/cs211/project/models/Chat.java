package cs211.project.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Chat {
    private ArrayList<ChatText> texts;
    public Chat(){
        texts = new ArrayList<>();
    }
    public void addNewText(LocalDateTime time, String username, String text){
        texts.add(new ChatText(time,username,text));
    }
    public ChatText findTextByUsername(String name){
        for(ChatText text : texts){
            if(text.isUsername(name)){
                return text;
            }
        }
        return null;
    }
    public ArrayList<ChatText> getChat() {
        return texts;
    }
}
