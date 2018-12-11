package com.company;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Main {
    public static final int PORT = 3000;
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT);
                Map<String, User> users = getUsersFromFile();

                while (true){
                    System.out.println("waiting to clients...");
                    Socket socket = serverSocket.accept();
                    File file = new File(String.valueOf(socket.getInputStream()));
                    new ClientThread(socket, users).start();
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

    private static Map<String, User> getUsersFromFile() {
        String basePath = "C:\\Kobi projects\\Home projects\\usersFile.txt";
        File usersFile = new File(basePath);
        Map<String, User> users = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(",");
                String userName = lineArr[0];
                String password = lineArr[1];

                User user = new User(userName, password);
                users.put(userName, user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

}
