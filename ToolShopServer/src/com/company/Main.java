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
                //The server starting to work
                while (true){
                    System.out.println("Waiting for clients...");
                    Socket socket = serverSocket.accept();
                    //File file = new File(String.valueOf(socket.getInputStream()));
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
//---All the Users are written and preserved in the memory and in the server file
    private static Map<String, User> getUsersFromFile() {
        File usersFile = new File("usersFile.txt");
        Map<String, User> users = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = br.readLine()) != null && line.length() > 0) {
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
