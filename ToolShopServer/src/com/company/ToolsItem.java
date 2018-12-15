package com.company;

import java.io.OutputStream;
import java.nio.ByteBuffer;

import java.io.IOException;
import java.io.InputStream;

public class ToolsItem {
    private String toolName;
    private int price;

    public ToolsItem(String toolName, int price) {
        this.toolName = toolName;
        this.price = price;
    }


    public ToolsItem(InputStream inputStream) throws IOException {
        int tNLength = inputStream.read();
        byte[] tNByte = new byte[tNLength];
        int actuallyRead;
        actuallyRead = inputStream.read(tNByte);
        if (actuallyRead != tNLength)
            throw new IOException("dddd");
        this.toolName = new String(tNByte);
        byte[] priceByte = new byte[4];
        actuallyRead = inputStream.read(priceByte);
        if (actuallyRead != 4)
            throw new IOException("ccccc");
        this.price = ByteBuffer.wrap(priceByte).getInt();
    }
    public void write(OutputStream outputStream) throws IOException{
        byte[] tNeBytes = toolName.getBytes();
        outputStream.write(tNeBytes.length);
        outputStream.write(tNeBytes);
        byte[] priceBytes = new byte[4];
        ByteBuffer.wrap(priceBytes).putInt(price);
        outputStream.write(priceBytes);
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
