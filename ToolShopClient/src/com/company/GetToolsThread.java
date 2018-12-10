package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class GetToolsThread extends Thread {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 3000;
    public static final int GET_TOOLS = 300;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean go;
    private User user;
    private ArrayList<UserCart> carts;



    public GetToolsThread( User user, ArrayList<UserCart> carts){
        this.carts = carts;
        this.user = user;
        go = true;
    }

    @Override
    public void run() {
        inputStream = null;
        outputStream = null;
        socket = null;
        try {
            while (go){
                socket = new Socket(HOST, PORT);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                outputStream.write(GET_TOOLS);

                user.write(outputStream);

                byte[] fromBytes = new byte[4];
                ByteBuffer.wrap(fromBytes).putInt(carts.size());
                outputStream.write(fromBytes);


            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

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

    public void stopGettingMessages(){
        go = false;
        interrupt();
    }
}
