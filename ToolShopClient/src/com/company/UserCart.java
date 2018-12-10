package com.company;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserCart {

    private String toolName;
    private int price;

    public UserCart(String toolName, int price) {
        this.toolName = toolName;
        this.price = price;
    }

    public UserCart(InputStream inputStream) throws IOException {
        int toolNameLength = inputStream.read();
        if (toolNameLength == -1)
            throw new IOException("fffff");
        byte[] toolNameBytes = new byte[toolNameLength];
        int actuallyRead = inputStream.read(toolNameBytes);
        if (actuallyRead != toolNameLength)
            throw new IOException("oooooo");
        this.toolName = new String(toolNameBytes);

        int priceLength = inputStream.read();
        if (priceLength == -1)
            throw new IOException("ahahahahah");
        byte[] priceBytes = new byte[priceLength];
        actuallyRead = inputStream.read(priceBytes);
        if (actuallyRead != priceLength)
            throw new IOException("yaaaaaaaaaaa");
        this.price = new  Integer(String.valueOf(priceBytes));
    }

    public void write(OutputStream outputStream) throws IOException {

        byte[] toolNameBytes = toolName.getBytes();
        outputStream.write(toolNameBytes.length);
        outputStream.write(toolNameBytes);
        byte[] priceBytes = new byte[4];
        ByteBuffer.wrap(priceBytes).putInt(price);
        outputStream.write(priceBytes.length);
        outputStream.write(priceBytes);
    }




    @Override
    public String toString() {
        return "UserCart{" +
                "toolName='" + toolName + '\'' +
                ", price=" + price +
                '}';
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
