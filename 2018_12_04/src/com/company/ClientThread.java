package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by hackeru on 15 נוב 18.
 */
public class ClientThread extends Thread {

    public static final int ADD = 100;
    public static final int SUBTRACT = 101;
    public static final int MULTIPLY = 102;
    public static final int DIVIDE = 103;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private int num1, num2;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            int action = inputStream.read();
            getOperands();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            switch (action){
                case ADD:
                    add();
                    break;
                case SUBTRACT:
                    subtract();
                    break;
                case MULTIPLY:
                    multiply();
                    break;
                case DIVIDE:
                    divide();
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void getOperands() throws IOException{
        byte[] buffer = new byte[4];
        int actuallyRead;
        actuallyRead = inputStream.read(buffer);
        if(actuallyRead != 4)
            throw new IOException("invalid first parameter");
        num1 = ByteBuffer.wrap(buffer).getInt();
        actuallyRead = inputStream.read(buffer);
        if (actuallyRead != 4)
            throw new IOException("invalid second parameter");
        num2 = ByteBuffer.wrap(buffer).getInt();
    }

    private void writeInteger(int x) throws IOException {
        byte[] xBytes = new byte[4];
        ByteBuffer.wrap(xBytes).putInt(x);
        outputStream.write(xBytes);
    }

    private void add() throws IOException{
        writeInteger(num1 + num2);

    }
    private void subtract() throws IOException{
        writeInteger(num1 - num2);
    }
    private void multiply() throws IOException{
        writeInteger(num1 * num2);
    }
    private void divide() throws IOException{
        if(num2 == 0)
            return;
        writeInteger(num1 / num2);
    }
}