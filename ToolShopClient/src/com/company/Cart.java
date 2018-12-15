package com.company;

import java.util.ArrayList;

public class Cart implements CartInterface {

    private ArrayList<Tool> tools;

    public Cart() {
        this.tools = new ArrayList<>();
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    @Override
    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    @Override
    public void clear() {
        this.tools.clear();
    }

    @Override
    public String printListToTotal() {
        String result = "Your cart:\n";
        int totalSum = 0;
        for (Tool tool: tools) {
            totalSum += tool.getPrice();
            result += tool.getToolName() + ": " + tool.getPrice() + "\n";
        }
        result += "--------------------------\n";
        result += "Total Sum: " + totalSum;

        return result;
    }
}
