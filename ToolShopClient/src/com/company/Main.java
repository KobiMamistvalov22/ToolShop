package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String userName, passWord;
        boolean isSignup = false;
        String input = "";

        boolean success = false;
        do {
            System.out.println("1. login");
            System.out.println("2. signup");
            input = s.nextLine();
            isSignup = input.equals("2");

            System.out.println("Enter userName: ");
            userName = s.nextLine();
            System.out.println("Enter passWord: ");
            passWord = s.nextLine();
            //file = new File(file + userName + ".txt");

            success = Server.login(userName, passWord, isSignup);
            if (!success) {
                System.out.println("Wrong credentials, Please try again...");
            }

        }while (!success);

        Tools tools = Server.toolsThread.getTools();
        int numberOfTools = tools.getNumberOfTools();
        int exitIndex = numberOfTools + 1;

        if (input.equals("1") && success){
            System.out.println("Welcome back " + userName);
        }else {
            System.out.println("You can start buying");
        }
        Cart cart = new Cart();
        int choice;
        while (true){
            System.out.println("\nplease choose: ");
            System.out.println(tools.printAllTools());
            System.out.println(exitIndex + ". exit");
            System.out.print("your choice: ");
            choice = readInteger();

            if(choice == exitIndex){
                System.out.println("OK good bye!");
                return;
            }

            if(choice != exitIndex){
                //printWriter.println(choice);
                //printWriter.close();
                if (choice <= numberOfTools) {
                    cart.addTool(tools.getToolInPosition(choice - 1));
                } else {
                    System.out.println("invalid choice");
                }

                System.out.println(cart.printListToTotal());
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Do you want more?");
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("5. Yes");
            System.out.println("6. No, Go to Buy!");
            choice = readInteger();

            switch (choice){
                case 5:
                    System.out.println("You are back");
                    continue;
                case 6:
                    cart.clear();
                    System.out.println("Thank you about your buying");
                    return;
                default:
                    System.out.println("invalid choice");
            }
        }

    }
    private static int readInteger(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return Integer.valueOf(input);
    }
    private static int readString(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return Integer.valueOf(input);
    }
}
