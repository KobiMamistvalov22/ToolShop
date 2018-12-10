package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userName, password;
        System.out.println("1. login");
        System.out.println("2. signup");
        String input = scanner.nextLine();
        boolean isSignup = input.equals("2");
        do{
            System.out.println("please enter your user name:");
            userName = scanner.nextLine();
            System.out.println("please enter your password:");
            password = scanner.nextLine();
        }while (!Server.login(userName, password, isSignup));
        System.out.println("login success!");
        List<Message> messages = new ArrayList<>();
        GetMessagesThread getMessagesThread = new GetMessagesThread(messages, new GetMessagesThread.NewMessageListener() {
            @Override
            public void onNewMessage(Message message) {
                System.out.println(message);
            }
        }, Server.user);
        getMessagesThread.start();

        System.out.println("type your message or 'exit' to close the app:");
        String message;

        while (!(message = scanner.nextLine()).equals("exit")){
            Server.sendMessage(message);
        }
        System.out.println("bye bye");


        getMessagesThread.stopGettingMessages();

    }


}