package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       /* Item tool1 = new Item("Kongo", 7500);
        Item tool2 = new Item("Patishon", 5000);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(0,tool1);
        items.add(1,tool2);
        System.out.println("your choices: ");
        System.out.println(items);
        for (Item item: items) {
            System.out.println(item);
            System.out.println("The total price is : " + item.getPrice());
        }
*/


        /*
        System.out.println(tool1);
        tool2.setPrice(2000);
        System.out.println(tool2.getPrice());
        System.out.println(tool1.getPrice() + tool2.getPrice());*/
        Scanner s = new Scanner(System.in);
        System.out.println("Name: ");
        String name = s.nextLine();
        File file = new File("C:\\Kobi projects\\Home projects\\ ");
        String data = "";
        try {
            file.createNewFile();
            file = new File(file + name+ ".txt");
            PrintWriter printWriter = new PrintWriter(file);
            System.out.println("Enter your text");
            printWriter.println(s.nextLine());
            printWriter.close();
            FileInputStream fileInputStream = new FileInputStream(file);
            int c;
            c = fileInputStream.read();
            while (c != -1){
                data += (char)c;
                c = fileInputStream.read();
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data: " + data);
    }
}
