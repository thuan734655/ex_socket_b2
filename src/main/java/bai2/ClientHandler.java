package bai2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private String username;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Enter your username:");
            username = in.readLine();
            ChatServer.broadcastMessage("[*] " + username + " has joined the chat.", this);

            String message;
            while ((message = in.readLine()) != null) {
                ChatServer.broadcastMessage(username + ": " + message, this);
            }
        } catch (IOException e) {
            System.out.println("[*] " + username + " has left the chat.");
        } finally {
            ChatServer.removeClient(this);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
