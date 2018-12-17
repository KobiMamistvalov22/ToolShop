package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String userName = "", passWord = "";
        boolean isSignup = false;
        String input = "";

        boolean success = false;
        //------------Login or Signup--------------
        do {
            System.out.println("1. login");
            System.out.println("2. signup");
            input = s.nextLine();
            if (!input.equals("1") && !input.equals("2")){
                System.out.println("invalid choice");
                continue;
            }

            isSignup = input.equals("2");

            System.out.println("Enter userName: ");
            userName = s.nextLine();
            System.out.println("Enter passWord: ");
            passWord = s.nextLine();
            success = Server.login(userName, passWord, isSignup);

            //---If in signup the userName exist or in login the userName/password not exist in the memory&&file
            if (!success) {
                System.out.println("Wrong credentials, Please try again...");
            }
        //---If the client signup/login in the memory&&file and shop Opened From the server side
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
                if (choice <= numberOfTools) {
                    cart.addTool(tools.getToolInPosition(choice - 1));
                } else {
                    System.out.println("invalid choice\n");
                }

                System.out.println(cart.printListToTotal());
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //---Option choose if the client want mor tools or buy and get out of the store
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
                    if (choice != 5 || choice != 6){
                        System.out.println("invalid choice");
                    }
            }
        }

    }
    private static int readInteger(){
        Scanner scanner = new Scanner(System.in);
       try {
           String input = scanner.nextLine();
           return Integer.valueOf(input);
       }catch (NumberFormatException e) {
           System.out.println("Try again");
          return readInteger();
       }
    }

}
