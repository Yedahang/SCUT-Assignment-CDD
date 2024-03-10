package com.development.scut_cdd.ServerLayer;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.development.scut_cdd.Config;

import Wifi.NetworkUtils;

public class ServerService extends Service {
    ServerSocket serverSocket=null;
    boolean closeSever=false;
    Context context;//上下文
    private Thread serverThread;
    Map<String, GameRoomRunnable> playerId_Map_PlayerRunnable;

    private ExecutorService executorService;//线程池

    ServerService serverService;

    String serverIpaddress;

    Message clinetMessager;//用来与客户端通信

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            Debug.waitForDebugger();
            //处理客户端的请求



        }

    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }

    public static final String TAG = "wangshu";
    @Override
    public void onCreate() {
//        Debug.waitForDebugger();
//        Log.d(TAG,"MyService is oncreate");
//        int pid = android.os.Process.myPid();
//        Log.d(TAG, "MyService is oncreate====="+"pid="+pid);
        //初始化相关
        //创建线程池
        context= getApplicationContext();
        executorService = Executors.newFixedThreadPool(2);
        playerId_Map_PlayerRunnable = new HashMap<>();
        serverService=this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        serverThread = new Thread(new ServerThread());
        serverThread.start();
//        executorService.submit(serverThread);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        Log.d(TAG,"OnDestory");
        //释放服务器资源
        if(serverSocket!=null){
            try{
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private class ServerThread implements Runnable {
        private GameRoomRunnable gameRoomRunnable=gameRoomRunnable =new GameRoomRunnable(context);
        @Override
        public void run() {
            executorService.submit(gameRoomRunnable);//提交房间线程
            try {

                serverSocket = new ServerSocket(8888);

                Log.d(Config.SOCKET_TAG, "Server 服务器已启动，等待客户端连接...");
                serverIpaddress= NetworkUtils.getIpAddress(context);
                Log.d(Config.SOCKET_TAG, "Server 本机IP地址：" +serverIpaddress+"  端口:8888");
                //初始化完成 现在可以提示
//                Debug.waitForDebugger();
                while (!Thread.currentThread().isInterrupted()) {
                    // 处理socket连接
                    Socket clientSocket = serverSocket.accept(); // 接受连接请求
                    Log.d(Config.SOCKET_TAG, "Server :" +"客户端——" + clientSocket.getInetAddress().getHostAddress()+" 已连接");
//                    Debug.waitForDebugger();
                    gameRoomRunnable.addPlayerSocket(clientSocket);
                    // 获取输入输出流
//                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
//                    String message="Hello,我是服务器";
//                    sendMessage(output,message);
                    //********************************************************************************************
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 发送消息
    public void sendMessage(PrintWriter output,String message) {
        if (output != null) {
            output.println(message);
        }
    }
}
