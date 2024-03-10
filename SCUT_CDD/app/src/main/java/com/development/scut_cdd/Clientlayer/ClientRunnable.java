package com.development.scut_cdd.Clientlayer;

import android.util.Log;

import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.Config;
import com.development.scut_cdd.ServerLayer.GameRoomData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Model.Entity.DataPackage;
import Model.Entity.ServerAction;

public class ClientRunnable implements Runnable{

    CardBoardActivity activity;
    private Socket client=null;
    private BufferedReader input;
    private PrintWriter output;
    private boolean running;

    private String MESSAGE =null;
    private String ipaddress;

    public ClientRunnable(CardBoardActivity activity,String ip) {
        this.activity = activity;
        ipaddress=ip;
    }

    @Override
    public void run() {
        while(client==null) {
            try {
//                Log.d(Config.TAG, "Client 本地客户端正在尝试连接........");
                // 创建Socket连接
                client = new Socket(ipaddress, 8888);

                Log.d(Config.SOCKET_TAG, "Client 本地客户端连接上:"+ipaddress);
                // 获取输入输出流
                input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                output = new PrintWriter(client.getOutputStream(), true);
                Log.d(Config.SOCKET_TAG, "Client 本地客户端已初始化");
                //发送id信息
                DataSender.getDataSender().sendData(ClientDataGenerator.comeIntoRoom());//加入房间
                running = true;
//                JSONObject jsonObject=new JSONObject();
//
//                try {
//                    jsonObject.put("test", "YEDAHANg");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//                String json = jsonObject.toString();
//                output.println(json);

                while (running) {
//                    Log.d(Config.TAG,"ClientRunnable while循环");
                    //发送消息
                    if(MESSAGE !=null){
                            output.println(MESSAGE);
//                            Log.d(Config.TAG, "ClientRunnable MESSAGE发送");
                            this.MESSAGE = null;
//
                    }
                    //收到消息
                    if(input.ready()) {
                        // 读取服务器发送的数据
                        Log.d(Config.TAG,"客户端收到信息");
                        String message = input.readLine();
                        if (message != null) {
                            // 处理接收到的数据
                            handleReceivedMessage(message);
                        }
                    }
                }

                // 关闭连接
                input.close();
                output.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 处理接收到的消息
    private void handleReceivedMessage(String message) {
        // 处理接收到的数据

        activity.getUiCon().handleData(GameRoomData.fromJson(message));

    }

    // 发送消息
    public void sendMessage(String message) {
        if (output != null) {
            output.println(message);
        }
    }

    public void setMESSAGE(String message){
        this.MESSAGE=message;
    }
    // 停止线程
    public void stopThread() {
        running = false;
    }


    public boolean isConnected(){
        return client.isConnected();
    }

    public PrintWriter getOutput() {
        return output;
    }

}
