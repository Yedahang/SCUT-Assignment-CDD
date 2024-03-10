package com.development.scut_cdd.ServerLayer;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import com.development.scut_cdd.Clientlayer.DataSender;
import com.development.scut_cdd.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Database.LocalDB;
import Model.Entity.DataPackage;
import Model.Entity.Player;
import Model.Entity.ServerAction;
import Model.Entity.UserAction;

public class GameRoomRunnable implements Runnable{

    Context context;

    //房间内玩家的socket
//   Map<String,Socket> PLAYERS_SOCKET= new HashMap<>();
    Socket client;
    Vector<Socket> sockets;//加入联机的用户

    Vector<PrintWriter> outputs;
    Vector<BufferedReader>inputs;
    Map<String,Boolean> responseMark;//收到回应消息
    Vector<String> Players_ID;

    ServerGameRoomHelper serverGameRoomHelper;
    boolean isGameRunning =false;

    private InputStream inputStream;//字节流
    private OutputStream outputStream;

    BufferedReader input;//字符流
    PrintWriter output;
    int Room_Id;
    public GameRoomRunnable(Context context) {
        serverGameRoomHelper =new ServerGameRoomHelper();
        responseMark=new HashMap<>();
        Players_ID=new Vector<>();
        sockets=new Vector<>();
        inputs=new Vector<>();
        outputs=new Vector<>();
        this.context = context;

    }
    public void sendData(String message){
        try {

            PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
            printWriter.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addPlayerSocket(Socket client) throws IOException {
        this.client=client;
        PrintWriter output_t=new PrintWriter(client.getOutputStream(), true);
        BufferedReader input_t = new BufferedReader(new InputStreamReader(client.getInputStream()));
        inputs.add(input_t);
        outputs.add(output_t);
    }

    @Override
    public void run() {
        while (true){
            try {
//                Debug.waitForDebugger();


                //拉取信息————————————————————————————————————
                for(BufferedReader bufferedReader:inputs){

                    if(bufferedReader.ready()) {
                        String message = bufferedReader.readLine();
                        handleMessage(message);
                    }
                }


//                当对局开始时候 同步信息
                if(isGameRunning&&isResponseMarkAllMark()){

                    //所有用户都响应了 可以更新下一轮了

                    //判断是否有赢家
//                    Debug.waitForDebugger();
                    String winner = serverGameRoomHelper.getWinner();
                    if(winner!=null){

                        Log.i(Config.TAG,"服务器线程 winner "+winner+"_________________________________________________________________________________");
                    }
//                    Debug.waitForDebugger();
                    Log.d(Config.SOCKET_TAG,"服务器线程 同步完消息");
//                    Debug.waitForDebugger();
                    serverGameRoomHelper.roundTheRoom(serverGameRoomHelper.getGameRoomData());
                    serverGameRoomHelper.getGameRoomData().setServerAction(new ServerAction(ServerAction.ACTION_In_Game));
                    broadcast(serverGameRoomHelper.getGameRoomData().toJson());
                    refreshResponseMark();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//

//
//
//        //等待其他玩家加入
//
//
//        //监听玩家操作
//        while(true){
//
//          //这里处理玩家操作
//        }
    }
    public void sendMessage(String message){
        if (output != null) {
            output.println(message);
        }

    }
    public void markResponse(String id){
          responseMark.put(id,true);
    }
    public void refreshResponseMark(){
        for(String s:Players_ID){
            if(responseMark.containsKey(s)){
                responseMark.put(s,false);
            }
        }
    }
    public boolean isResponseMarkAllMark(){
        for(String s:Players_ID){
            if(Boolean.FALSE.equals(responseMark.get(s))){
                return false;
            }
        }
        return true;
    }

    /**
     * 描述:广播消息
     * @author 叶达杭
     * @param message 添加说明
     * @return void 添加说明
    */
    private void broadcast(String message){
        for(PrintWriter p:outputs){
            p.println(message);
        }
        refreshResponseMark();
    }
    private void handleMessage(String message){
//        Debug.waitForDebugger();
//        Debug.waitForDebugger();
        Log.d(Config.TAG,"服务器线程 进入handleMessage");
        //处理收到的消息
//        Debug.waitForDebugger();
        GameRoomData gameRoomData =GameRoomData.fromJson(message);

        markResponse(gameRoomData.getNewAction().getUSER_ID());
        switch (gameRoomData.getNewAction().getACTION_TYPE()){
            case UserAction.ACTION_TYPE_NEW_A_GAME:  //开始一局新游戏
                if(Players_ID.size()==4){
                    //人满了 可以开始游戏
                    serverGameRoomHelper.startTurn();
                    isGameRunning=true;
                    broadcast(serverGameRoomHelper.getServerDataGenerator().gen_Initial_Game());//广播初始化

                    Log.d(Config.TAG,"服务器线程 处理ACTION_TYPE_NEW_A_GAME 房间初始化完成 可以开始游戏");
                }

//                sendData(serverGameRoomHelper.getServerDataGenerator().gen_Initial_Game());

//              Log.d(Config.TAG,"PlayerRunnable收到UserAction.ACTION_TYPE_NEW_A_GAME");
//              output.println(dataPackage.toJson());

                break;
            case UserAction.ACTION_TYPE_NO_ACTION:
//                Debug.waitForDebugger();
                Log.d(Config.SOCKET_TAG,"服务器线程 处理ACTION_TYPE_NO_ACTION");
                break;
            case UserAction.ACTION_TYPE_COME_INTO_ROOM://玩家进入房间初始化名字
                //初始化 响应集合
                Players_ID.add(gameRoomData.getNewAction().getUSER_ID());
                responseMark.put(gameRoomData.getNewAction().getUSER_ID(),false);
                serverGameRoomHelper.addPlayerToRoom(gameRoomData.getNewAction().getUSER_ID());//玩家加入房间
                broadcast(serverGameRoomHelper.getServerDataGenerator().ANewPlayerJion());//广播消息
                Log.d(Config.SOCKET_TAG,"服务器线程 处理ACTION_TYPE_COME_INTO_ROOM");
                break;
//                case UserAction
            case UserAction.ACTION_TYPE_SHOW:
//                Debug.waitForDebugger();
                serverGameRoomHelper.setGameRoomData(gameRoomData);
                Log.d(Config.SOCKET_TAG,"服务器线程 处理ACTION_TYPE_SHOW");
                break;
            case UserAction.ACTION_TYPE_PASS:
//                Debug.waitForDebugger();
                serverGameRoomHelper.setGameRoomData(gameRoomData);
                Log.d(Config.SOCKET_TAG,"服务器线程 处理ACTION_TYPE_PASS");
                break;
            case UserAction.ACTION_TYPE_ADD_ROBOT:
                //增加机器人
//                Debug.waitForDebugger();
                Players_ID.add(serverGameRoomHelper.addRobotToRoom());
                broadcast(serverGameRoomHelper.getServerDataGenerator().ANewPlayerJion());//广播消息
                Log.d(Config.SOCKET_TAG,"服务器线程 处理ACTION_TYPE_ADD_ROBOT");
                break;
            case UserAction.ACTION_TYPE_SINGLE_PLAYING:
                serverGameRoomHelper.initRoom();
                Players_ID.add(serverGameRoomHelper.gameRoomData.getPlayers().get(1).getUSER_ID());
                Players_ID.add(serverGameRoomHelper.gameRoomData.getPlayers().get(2).getUSER_ID());
                Players_ID.add(serverGameRoomHelper.gameRoomData.getPlayers().get(3).getUSER_ID());

//                serverGameRoomHelper.startTurn();
                broadcast(serverGameRoomHelper.getServerDataGenerator().singlePlayInit());//广播初始化/
                break;
            default:
                Log.d(Config.SOCKET_TAG,"PlayerRunnable default");
                break;
        }

    }


}
