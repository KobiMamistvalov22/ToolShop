package com.company;

import java.util.ArrayList;

public class Tools {
    private ArrayList<Tool> tools;

    public Tools() {
        this.tools = new ArrayList<>();
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    public String printAllTools() {
        String result = "";
        int i = 1;
        for (Tool tool: this.tools) {
            result += i + ". " + tool.getToolName() + " = " + tool.getPrice() + "$\n";
            i++;
        }

        return result;
    }

    public Tool getToolInPosition(int i) {
        return this.tools.get(i);
    }
    public int getNumberOfTools() {
        return this.tools.size();
    }
}
