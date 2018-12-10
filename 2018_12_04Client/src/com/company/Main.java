package com.company;

import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CalculatorConnection.setListener(new CalculatorConnection.CalculatorListener() {
            @Override
            public void onResult(int result) {
                System.out.println("the result is: " + result);
            }
        });

        int choice;
        //while (){
        while (true) {
            System.out.println("please choose: ");
            System.out.println("1. add");
            System.out.println("2. subtract");
            System.out.println("3. multiply");
            System.out.println("4. divide");
            System.out.println("5. exit");
            System.out.print("your choice: ");
            choice = readInteger();
            if (choice == 5) {
                System.out.println("c ya later alligator!");
                return;
            }
            System.out.println("enter first integer:");
            int num1 = readInteger();
            System.out.println("enter second integer:");
            int num2 = readInteger();
            switch (choice) {
                case 1:
                    CalculatorConnection.add(num1, num2);
                    break;
                case 2:
                    CalculatorConnection.subtract(num1, num2);
                    break;
                case 3:
                    CalculatorConnection.multiply(num1, num2);
                    break;
                case 4:
                    if (num2 == 0) {
                        System.out.println("go learn some math...");
                        continue;
                    }
                    CalculatorConnection.divide(num1, num2);
                    break;
                default:
                    System.out.println("invalid choice");
            }
        }
    //}
    }

    private static int readInteger(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return Integer.valueOf(input);
    }
}