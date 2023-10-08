package cs211.project.repository;

import cs211.project.models.Chat;
import cs211.project.models.TeamList;
import cs211.project.services.ChatDatasource;
import cs211.project.services.Datasource;
import cs211.project.services.TeamDatasource;

public class ChatRepository {
    private Chat chatList;
    private Datasource<Chat> datasource;

    public ChatRepository(){
        datasource = new ChatDatasource("data", "chat.csv");
        chatList = datasource.readData();
    }

    public void save(Chat chatList){ datasource.writeData(chatList); }

    public Chat getChatList(){return chatList;}
}
