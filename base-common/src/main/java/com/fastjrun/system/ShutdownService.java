package com.fastjrun.system;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.fastjrun.helper.IOHelper;

public class ShutdownService implements Runnable {
    private int port;
    private static final String shutdownCommand = "shutdown";

    public ShutdownService(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = IOHelper.read(inputStream);
            socket.shutdownInput();
            String message = new String(bytes);
            if (shutdownCommand.equalsIgnoreCase(message)) {
                inputStream.close();
                socket.close();
                serverSocket.close();
            }
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
