package com.company;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Tools {
    private ArrayList<ToolsItem> toolsItems;

    public Tools(ArrayList<ToolsItem> toolsItems) {
        this.toolsItems = toolsItems;
    }

    public Tools(){
        ToolsItem tool1 = new ToolsItem("Jackhammer", 33);
        ToolsItem tool2 = new ToolsItem("Hammer", 10);
        ToolsItem tool3 = new ToolsItem("Angle Grinder", 140);
        ToolsItem tool4 = new ToolsItem("Toy", 250);

        this.toolsItems = new ArrayList<ToolsItem>();
        this.toolsItems.add(tool1);
        this.toolsItems.add(tool2);
        this.toolsItems.add(tool3);
        this.toolsItems.add(tool4);
    }

    public void write(OutputStream outputStream) throws IOException {
        outputStream.write(this.toolsItems.size());

        for (ToolsItem tool: this.toolsItems) {
            byte[] toolName = tool.getToolName().getBytes();
            int tool1Price = tool.getPrice();

            outputStream.write(toolName.length);
            outputStream.write(toolName);

            outputStream.write(tool1Price);
        }



//        outputStream.write(toolNameBytes.length);
//        outputStream.write(toolNameBytes);
//        byte[] priceBytes = new byte[4];
//        ByteBuffer.wrap(priceBytes).putInt(price);
//        outputStream.write(priceBytes.length);
//        outputStream.write(priceBytes);
    }

 /*   public void addTool(int numChoice) {

        if(numChoice == 1)
            toolsItems[size] = new Product(productName, 10);
        else if(numChoice == 2)
            toolsItems[size] = new Product(productName, 20);
        else
            toolsItems[size] = new Product(productName, 12);
        size++;
    }


    public void add(String toolName, int price){
        ToolsItem newTool = new ToolsItem(toolName, price);
        toolsItems[size] = newTool;
        size++;
    }




    public void show(){
        for (int i = 1; i <= size; i++) {
            System.out.println(i + ". " + toolsItems[i].getToolName() + ", price: " + toolsItems[i].getPrice());
        }
    }



    private String toolName;
    private int price;

    public ToolsStore(String toolName, int price){
        this.toolName = toolName;
        this.price = price;
    }
    public ToolsStore(InputStream inputStream) throws IOException {
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
    }*/
}
