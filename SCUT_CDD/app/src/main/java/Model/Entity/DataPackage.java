package Model.Entity;

import com.development.scut_cdd.ServerLayer.GameRoomData;
import com.google.gson.Gson;

public class DataPackage {
    RoomModel roomModel=null;
    PlayerModel playerModel=null;

    UserAction userAction=null;


    Player player;

    GameRoomData gameRoomData;
    public DataPackage() {
    }

    public DataPackage(RoomModel roomModel, PlayerModel playerModel, UserAction userAction, Player player) {
        this.roomModel = roomModel;
        this.playerModel = playerModel;
        this.userAction = userAction;
        this.player = player;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public UserAction getUserAction() {
        return userAction;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public void setUserAction(UserAction userAction) {
        this.userAction = userAction;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameRoomData getServerGameRoomData() {
        return gameRoomData;
    }

    public void setServerGameRoomData(GameRoomData gameRoomData) {
        this.gameRoomData = gameRoomData;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static DataPackage fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, DataPackage.class);
    }

}
