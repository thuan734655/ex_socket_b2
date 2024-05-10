package bai2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "";
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to the chat server.");


            Thread messageThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            messageThread.start();

            String userInput;
            while ((userInput = reader.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
