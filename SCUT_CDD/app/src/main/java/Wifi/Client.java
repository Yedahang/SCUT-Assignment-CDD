package Wifi;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/** <p>客户端线程</p>*/
public class Client {
    public static final String SERVER_IP = "192.168.137.42";
    public static final int SERVER_PORT = 8888;
    private static final String TAG = "TEST_Client";


//    @Override
//    public void run() {
//        try {
//            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
//            Log.d(TAG,"已连接到服务器：" + SERVER_IP);
//
//
//
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) {
        BufferedReader input;
        PrintWriter output;
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
//            Log.d(TAG,"已连接到服务器：" + SERVER_IP);
            System.out.println("已连接到服务器：" + SERVER_IP);
            // 获取输入输出流
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            while (true){
                // 读取服务器发送的数据
                String message = input.readLine();
                System.out.println(message);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
