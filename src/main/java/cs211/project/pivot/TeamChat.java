package cs211.project.pivot;

public class TeamChat {
    private int teamId;
    private int chatId;

    public TeamChat(int teamId, int chatId){
        this.teamId = teamId;
        this.chatId = chatId;
    }
    public boolean isTeamId(int id){
        return this.teamId == id;
    }
    public boolean isChatId(int id){return this.chatId == id;}
    public int getTeamId() {
        return teamId;
    }
    public int getChatId() {
        return chatId;
    }

    @Override
    public String toString() {
        return "TeamChat{" +
                "teamId=" + teamId +
                ", chatId=" + chatId +
                '}';
    }
}
