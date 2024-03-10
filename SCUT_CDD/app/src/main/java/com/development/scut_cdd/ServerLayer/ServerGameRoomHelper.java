package com.development.scut_cdd.ServerLayer;

import android.os.Debug;
import android.util.Log;

import com.development.scut_cdd.Config;

import Database.LocalDB;
import Model.Entity.Player;
import Model.Entity.Robot;
import Model.Entity.ServerAction;
import Model.Entity.UserAction;
import Model.ServiceImpl.Entity_RobotServiceImpl.RobotImpl;
import Model.ServiceImpl.GameTurnServiceImpl;

public class ServerGameRoomHelper {
    GameRoomData gameRoomData;

    int robotNum=0;
    ServerDataGenerator serverDataGenerator;
    public ServerGameRoomHelper() {
        gameRoomData =new GameRoomData();
    }

    public void setGameRoomData(GameRoomData gameRoomData) {
        this.gameRoomData = gameRoomData;
    }

    public void addPlayer(Player player){
        gameRoomData.addPlayer(player);
        serverDataGenerator=new ServerDataGenerator(gameRoomData);
    }

    public void addPlayerToRoom(String player_id){
        addPlayer(new Player(player_id,Player.TYPE_USER));
    }

    public String addRobotToRoom(){
        String robotId="Robot"+robotNum;
        addPlayer(new Robot(robotId,Player.TYPE_ROBOT));
        robotNum++;
        return robotId;
    }
    public void initRoom(){
        addPlayer(new Robot("Robot1",Player.TYPE_ROBOT));
        addPlayer(new Robot("Robot2",Player.TYPE_ROBOT));
        addPlayer(new Robot("Robot3",Player.TYPE_ROBOT));
    }
    /**
     * 描述: 开始对局
     * @author 叶达杭
     * @return void 添加说明
    */
    public void startTurn(){
        GameTurnServiceImpl gameTurnService1 =new GameTurnServiceImpl();
        gameTurnService1.distributeCard(gameRoomData.getPlayers());
        //决定出牌顺序
        gameTurnService1.decideShowingOrder(gameRoomData);
//        gameRoomData.setCurrentPlayerIndex(0);

    }
    public String getWinner(){
        for(Player p:gameRoomData.getPlayers()){
            if(p.getOwnCardGroup().getCards().isEmpty()){
                return p.getUSER_ID();
            }
        }
        return null;
    }
    //
    public void roundTheRoom(GameRoomData gameRoomData1){
        if(gameRoomData1.getCurrentPlayer().getType().equals(Player.TYPE_ROBOT)){
            //机器人
            if(gameRoomData1.getRoundCount()==0){
                Log.d(Config.TAG,"首家是机器人，出牌");
                //如果首家是机器人
                Robot temp= (Robot) gameRoomData1.getCurrentPlayer();
                temp.showDiamondThree();
                gameRoomData1.setPreviousPlayerId(getGameRoomData().getCurrentPlayerIndex());
                gameRoomData1.updateTONextPlayerIndex();
                gameRoomData1.setNewAction(new UserAction());
                gameRoomData1.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_SHOW);//出牌
                gameRoomData1.setServerAction(new ServerAction(ServerAction.ACTION_In_Game));
                gameRoomData1.getNewAction().setUSER_ID(temp.getUSER_ID());
//                gameRoomData.setServerAction(new ServerAction());
            }else{
                //不是首家
//                Debug.waitForDebugger();
                Player robot= gameRoomData1.getCurrentPlayer();
                if(gameRoomData1.getPassCount()==3){
//                    Debug.waitForDebugger();
                    //又回到自己了
                    gameRoomData1.clearPassCount();
                    //随机出一张
                    gameRoomData1.clearPassCount();
                    gameRoomData1.setPreviousPlayerId(gameRoomData1.getCurrentPlayerIndex());
                    RobotImpl.robotShowMin(robot,gameRoomData1);
                    gameRoomData1.updateTONextPlayerIndex();
                    gameRoomData1.setNewAction(new UserAction());
                    gameRoomData1.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_SHOW);//出牌
                    gameRoomData1.setServerAction(new ServerAction(ServerAction.ACTION_In_Game));
                    gameRoomData1.getNewAction().setUSER_ID(robot.getUSER_ID());

                    return;
                }
                if(RobotImpl.robotTurn(robot,gameRoomData1.getPreviousPlayer().getShownCards(),gameRoomData1)){
                    gameRoomData1.setPreviousPlayerId(gameRoomData1.getCurrentPlayerIndex());
                    //清楚passCount

                    gameRoomData1.updateTONextPlayerIndex();
                    gameRoomData1.setNewAction(new UserAction());
                    gameRoomData1.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_SHOW);//出牌
                    gameRoomData1.setServerAction(new ServerAction(ServerAction.ACTION_In_Game));
                    gameRoomData1.getNewAction().setUSER_ID(robot.getUSER_ID());
                }else{
                    //pass
                    gameRoomData1.setPreviousPlayerId(gameRoomData1.getCurrentPlayerIndex());
                    gameRoomData1.setPassPlayerID(robot.getUSER_ID());
                    gameRoomData1.upPassCount();
                    gameRoomData1.updateTONextPlayerIndex();
                    gameRoomData1.setNewAction(new UserAction());
                    gameRoomData1.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_PASS);//跳过
                    gameRoomData1.setServerAction(new ServerAction(ServerAction.ACTION_In_Game));
                    gameRoomData1.getNewAction().setUSER_ID(robot.getUSER_ID());
                }

            }
        }
    }

    public GameRoomData getGameRoomData() {
        return gameRoomData;
    }

    public ServerDataGenerator getServerDataGenerator() {
        return serverDataGenerator;
    }
}
