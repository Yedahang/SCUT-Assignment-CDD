package com.development.scut_cdd.Clientlayer;

import android.util.Log;

import com.development.scut_cdd.Config;

public class DataSender {
    ClientRunnable clientRunnable;

    static DataSender dataSender;

    public DataSender(ClientRunnable clientRunnable) {
        this.clientRunnable = clientRunnable;
        dataSender=this;
    }
    public static DataSender getDataSender(){
        return dataSender;
    }
    public void sendData(String data){
        while (!clientRunnable.isConnected());
        clientRunnable.setMESSAGE(data);
        Log.d(Config.SOCKET_TAG,"Client DataSender");
    }

}
