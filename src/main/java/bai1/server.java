package bai1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("server listen");

            while (true) {
               Socket socketClient = serverSocket.accept();

                System.out.println("accept");

                Thread thread = new Thread(new HandleCLient(socketClient));
                thread.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}
