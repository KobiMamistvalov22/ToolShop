package com.company;

public class Item {
    private String tool;
    private int price;

    public Item(){
        this.tool = "";
        this.price = 0;
    }
    public Item(String tool, int price){
        this.tool = tool + "";
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item " + tool + " price = " + price ;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
