package bai1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("",1234);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = "";
            while ((data = reader.readLine()) != null) {
                System.out.println(data);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
