package com.development.scut_cdd.Clientlayer;


import android.util.Log;

import com.development.scut_cdd.Config;
import com.development.scut_cdd.ServerLayer.GameRoomData;

import ControllerImpl.UIConImpl;
import Database.LocalDB;
import Model.Entity.SelectedCardGroup;
import Model.Entity.ServerAction;
import Model.Entity.UserAction;

public class ClientDataGenerator {

    /**
     * 描述:发送数据包 开始一局新游戏
     * @author 叶达杭
     * @return void 添加说明
    */
    public static String comeIntoRoom(){
        GameRoomData temp = new GameRoomData();
        temp.setNewAction(new UserAction());
        temp.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_COME_INTO_ROOM);
        temp.getNewAction().setUSER_ID(Config.USER_ID);
        return temp.toJson();
    }

    public static String startNewGame(){
        Log.d(Config.TAG,"客户端:发送消息开始一局新游戏");
        GameRoomData temp = new GameRoomData();
        temp.setNewAction(new UserAction());
        temp.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_NEW_A_GAME);
        temp.getNewAction().setUSER_ID(Config.USER_ID);
        return temp.toJson();
    }
    public static String passAction(){
        GameRoomData temp = UIConImpl.getGameRoomData();
        temp.getCurrentPlayer().setPass(true);
//        temp.setPreviousPlayerId(temp.getCurrentPlayerIndex());//
        temp.updateTONextPlayerIndex();
        temp.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_PASS);//跳过
        temp.upPassCount();
//        temp.setServerAction(new ServerAction(ServerAction.ACTION_In_Game));
        temp.getNewAction().setUSER_ID(Config.USER_ID);
        return temp.toJson();

    }
    public static String responseWithNoAction(){
        GameRoomData temp = new GameRoomData();
        temp.setNewAction(new UserAction());
        temp.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_NO_ACTION);
        temp.getNewAction().setUSER_ID(Config.USER_ID);
        return temp.toJson();
    }
    public static String responseWithShowing(SelectedCardGroup scg,String user_id){
        GameRoomData gameRoomData=UIConImpl.getGameRoomData();
        gameRoomData.getCurrentPlayer().setShownCards(scg);
        gameRoomData.setPreviousPlayerId(gameRoomData.getCurrentPlayerIndex());
        gameRoomData.updateTONextPlayerIndex();
        gameRoomData.setNewAction(new UserAction());
        gameRoomData.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_SHOW);//出牌
        gameRoomData.setServerAction(new ServerAction(ServerAction.ACTION_In_Game));
        gameRoomData.getNewAction().setUSER_ID(user_id);
        return gameRoomData.toJson();
    }
    public static String addRobotRoRoom(){
        GameRoomData gameRoomData = new GameRoomData();
        gameRoomData.setNewAction(new UserAction());
        gameRoomData.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_ADD_ROBOT);
        gameRoomData.getNewAction().setUSER_ID(Config.USER_ID);

        return gameRoomData.toJson();

    }
    public static String playSingle(){
        GameRoomData gameRoomData = new GameRoomData();
        gameRoomData.setNewAction(new UserAction());
        gameRoomData.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_SINGLE_PLAYING);
        gameRoomData.getNewAction().setUSER_ID(Config.USER_ID);
        return gameRoomData.toJson();
    }


//    public static String ShowingCards(){
//        Log.d(Config.TAG,"客户端:出牌");
//        GameRoomData temp= new GameRoomData();
//        temp.setNewAction(new UserAction());
//        temp.getNewAction().setACTION_TYPE(UserAction.ACTION_TYPE_SHOW);
//
//    }

}
