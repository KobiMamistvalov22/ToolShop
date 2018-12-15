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
                    System.out.println("Waiting for clients...");
                    Socket socket = serverSocket.accept();
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
        File usersFile = new File("usersFile.txt");
        Map<String, User> users = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
            String line = br.readLine();
            while (line != null && line.length() > 0) {
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
