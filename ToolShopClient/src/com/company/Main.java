package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String userName, passWord;
        UserCart tool1 = new UserCart("Jackhammer", 1500);
        UserCart tool2 = new UserCart("Hammer", 1500);
        UserCart tool3 = new UserCart("Angle Grinder", 1500);
        File file = new File("C:\\Kobi projects\\Home projects\\ ");
        System.out.println("1. login");
        System.out.println("2. signup");
        String input = s.nextLine();
        boolean isSignup = input.equals("2");
        //do {
            System.out.println("Enter userName: ");
            userName = s.nextLine();
            System.out.println("Enter passWord: ");
            passWord = s.nextLine();
            file.createNewFile();
            file = new File(file + userName + ".txt");
        //}while (!Server.login(userName, passWord, isSignup));

        System.out.println("login");
        PrintWriter printWriter = new PrintWriter(file);
        ArrayList<UserCart> tools = new ArrayList<>();
        //printWriter.println(readInteger());
        FileInputStream fileInputStream = new FileInputStream(file);
        int choice;
        while (true){
            System.out.println("please choose: ");
            System.out.println("1. Jackhammer = 1,500$");
            System.out.println("2. Hammer = 10$");
            System.out.println("3. Angle Grinder = 1,400$");
            System.out.println("4. exit");
            System.out.print("your choice: ");
            choice = readInteger();
            if(choice == 4){
                System.out.println("OK good bye!");
                return;
            }

            if(choice == 1||choice == 2|| choice == 3){
                System.out.println("Here your tools: " + fileInputStream);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Do you won't more?");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("5. Yes");
            System.out.println("6. No, Go to Buy!");
            choice = readInteger();

            switch (choice){
                case 1:
                    tools.add(0,tool1);
                    break;
                case 2:
                    tools.add(1,tool2);
                    break;
                case 3:
                    tools.add(2,tool3);
                    break;
                case 5:
                    System.out.println("You are back");
                    break;
                case 6:
                    tools.clear();
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
}
