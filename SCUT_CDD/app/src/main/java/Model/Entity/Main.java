package Model.Entity;

import com.google.gson.Gson;

// 定义游戏信息类
class GameInfo {
    private int playerId;
    private String action;
    private String card;

    public GameInfo(int playerId, String action, String card) {
        this.playerId = playerId;
        this.action = action;
        this.card = card;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getAction() {
        return action;
    }

    public String getCard() {
        return card;
    }
}

public class Main {
    public static void main(String[] args) {
        // 创建游戏信息对象
        GameInfo gameInfo = new GameInfo(1, "play_card", "A♠");

        // 使用Gson库进行编码和解码
        Gson gson = new Gson();

        // 编码为JSON格式
        String json = gson.toJson(gameInfo);
        System.out.println("编码后的JSON字符串：" + json);

        // 解码为对象
        GameInfo decodedGameInfo = gson.fromJson(json, GameInfo.class);
        System.out.println("解码后的对象：" + decodedGameInfo.getPlayerId() + ", " +
                decodedGameInfo.getAction() + ", " + decodedGameInfo.getCard());
    }
}
