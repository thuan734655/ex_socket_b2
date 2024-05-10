package bai1;

import java.io.OutputStream;
import java.net.Socket;

public class HandleCLient implements Runnable {

    Socket socket = null;

    public HandleCLient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            for(int i = 1; i <= 1000; i++) {
                outputStream.write((i + "\n").getBytes());
                outputStream.flush();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
