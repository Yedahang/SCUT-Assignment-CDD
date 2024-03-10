package Wifi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NetworkScanner {
    public static List<String> scanDevices(String subnet) {
        List<String> devicesList = new ArrayList<>();

        for (int i = 1; i < 255; i++) {
            String host = subnet + "." + i;
            try {
                InetAddress inetAddress = InetAddress.getByName(host);
                if (inetAddress.isReachable(1000)) {
                    devicesList.add(host);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return devicesList;
    }
}
