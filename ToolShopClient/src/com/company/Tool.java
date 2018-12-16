package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class Tool {

    private String toolName;
    private int price;

    public Tool(String toolName, int price) {
        this.toolName = toolName;
        this.price = price;
    }
//-------Get the toolName and the price from the server
    public Tool(InputStream inputStream) throws IOException {
        int toolNameLength = inputStream.read();
        if (toolNameLength == -1)
            throw new IOException("fffff");
        byte[] toolNameBytes = new byte[toolNameLength];
        int actuallyRead = inputStream.read(toolNameBytes);
        if (actuallyRead != toolNameLength)
            throw new IOException("oooooo");
        this.toolName = new String(toolNameBytes);

        int price = inputStream.read();
        byte[] priceBytes = new byte[4];
        actuallyRead = inputStream.read(priceBytes);
        if (actuallyRead != 4)
            throw new IOException("ccccc");
        this.price = ByteBuffer.wrap(priceBytes).getInt();
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
