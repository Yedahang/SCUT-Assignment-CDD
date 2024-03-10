package com.development.scut_cdd.ServerLayer;

import com.google.gson.Gson;

import java.util.Vector;

import Model.Entity.Player;
import Model.Entity.SelectedCardGroup;
import Model.Entity.ServerAction;
import Model.Entity.UserAction;

/** <p>服务器一个房间线程数据</p>*/
public class GameRoomData {
    Vector<Player> players=new Vector<>();

    SelectedCardGroup previousShownCards;
    UserAction newAction;
    ServerAction serverAction;
    int playerCount;
    int currentPlayerIndex;


    int previousPlayerId;//上一家
    int passCount;//跳过统计

    String passPlayerID;
    int roundCount;



    boolean isTheFirstPlayerShowFlag=true;



    public GameRoomData() {
        playerCount=0;
    }


    public void setServerAction(ServerAction serverAction) {
        this.serverAction = serverAction;
    }

    public String getCurrentPlayerId(){
        return players.get(currentPlayerIndex).getUSER_ID();
    }
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }
    public ServerAction getServerAction() {
        return serverAction;
    }

    public void setCurrentPlayerSelectedCard(){

    };
    public void addPlayer(Player player){
        players.add(player);
        playerCount++;
    }

    public Vector<Player> getPlayers() {
        return players;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setPlayers(Vector<Player> players) {
        this.players = players;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public void setTheFirstPlayerShowFlag(boolean theFirstPlayerShowFlag) {
        isTheFirstPlayerShowFlag = theFirstPlayerShowFlag;
    }

    public boolean isTheFirstPlayerShowFlag() {
        return isTheFirstPlayerShowFlag;
    }
    public void updateTONextPlayerIndex(){
        currentPlayerIndex =(currentPlayerIndex +1)%4;
        roundCount++;

    }
    public void currentPlayerPass(){
        passCount++;
        updateTONextPlayerIndex();
    }

    public void setPreviousShownCards(SelectedCardGroup previousShownCards) {
        this.previousShownCards = previousShownCards;
    }

    public void setNewAction(UserAction newAction) {
        this.newAction = newAction;
    }

    public void setPreviousPlayerId(int previousPlayerId) {
        this.previousPlayerId = previousPlayerId;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public SelectedCardGroup getPreviousShownCards() {
        return getPreviousPlayer().getShownCards();
    }

    public UserAction getNewAction() {
        return newAction;
    }

    public Player getPreviousPlayer() {
        return players.get(previousPlayerId);
    }

    public int getPassCount() {
        return passCount;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static GameRoomData fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, GameRoomData.class);
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public int getRoundCount() {
        return roundCount;
    }
    public void clearPassCount(){
        passCount=0;
    }
    public void setPassPlayerID(String passPlayerID) {
        this.passPlayerID = passPlayerID;
    }

    public int getPreviousPlayerId() {
        return previousPlayerId;
    }

    public String getPassPlayerID() {
        return passPlayerID;
    }
    public void upPassCount(){
        passCount++;
    }
}
