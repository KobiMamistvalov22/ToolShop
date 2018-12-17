package com.company;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Tools {
    private ArrayList<ToolsItem> toolsItems;

    public Tools(ArrayList<ToolsItem> toolsItems) {
        this.toolsItems = toolsItems;
    }
//---List of tools
    public Tools(){
        ToolsItem tool1 = new ToolsItem("Jackhammer", 1500);
        ToolsItem tool2 = new ToolsItem("Hammer", 10);
        ToolsItem tool3 = new ToolsItem("Angle Grinder", 1400);
        ToolsItem tool4 = new ToolsItem("RP 340 Press Tool", 1600);

        this.toolsItems = new ArrayList<ToolsItem>();
        this.toolsItems.add(tool1);
        this.toolsItems.add(tool2);
        this.toolsItems.add(tool3);
        this.toolsItems.add(tool4);
    }
//---Transferring all the tools to client side
    public void write(OutputStream outputStream) throws IOException {
        outputStream.write(this.toolsItems.size());

        for (ToolsItem tool: this.toolsItems) {
            byte[] toolName = tool.getToolName().getBytes();
            outputStream.write(toolName.length);
            outputStream.write(toolName);

            byte[] tool1Price = new byte[4];
            ByteBuffer.wrap(tool1Price).putInt(tool.getPrice());
            outputStream.write(tool1Price.length);
            outputStream.write(tool1Price);
        }

    }

}
