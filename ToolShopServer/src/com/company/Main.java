package com.company;


import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static final int PORT = 3000;
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT);
                ArrayList<ToolsStore> toolsStores = new ArrayList<>();
                Map<String, User> users = new HashMap<>();
                while (true){
                System.out.println("waiting to clients...");
                Socket socket = serverSocket.accept();
                File file = new File(String.valueOf(socket.getInputStream()));
                new ClientThread(socket, toolsStores, users).start();
            }

        } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(serverSocket != null){
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
