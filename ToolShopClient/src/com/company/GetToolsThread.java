package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
//---Here the tools connecting and come from the server

public class GetToolsThread extends Thread {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 3000;
    public static final int GET_TOOLS = 250;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Tools tools;

    @Override
    public void run() {
        inputStream = null;
        outputStream = null;
        socket = null;
        try {
            socket = new Socket(HOST, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            outputStream.write(GET_TOOLS);

            int numberOfTools = inputStream.read();
            this.tools = new Tools();

            for (int i = 0; i < numberOfTools; i++) {
                Tool tool = new Tool(inputStream);
                tools.addTool(tool);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Tools getTools() {
        return this.tools;
    }
}
