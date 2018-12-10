package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * Created by hackeru on 15 נוב 18.
 */
public class CalculatorConnection {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 3000;
    public static final int ADD = 100;
    public static final int SUBTRACT = 101;
    public static final int MULTIPLY = 102;
    public static final int DIVIDE = 103;

    private static CalculatorListener listener;


    public static void setListener(CalculatorListener listener) {
        CalculatorConnection.listener = listener;
    }

    private static void connection(int num1, int num2, int operator) {
        Thread thread = new CalculatorThread(num1, num2, operator, listener);
        thread.start();
    }


    public static void add(int num1, int num2){
        connection(num1, num2, ADD);
    }

    public static void subtract(int num1, int num2){
        connection(num1, num2, SUBTRACT);
    }

    public static void multiply(int num1, int num2){
        connection(num1, num2, MULTIPLY);
    }

    public static void divide(int num1, int num2){
        if(num2 == 0)
            throw new RuntimeException("division by zero!");
        connection(num1, num2, DIVIDE);
    }


    static class CalculatorThread extends Thread{

        private int num1, num2, operator;
        private CalculatorListener listener;

        public CalculatorThread(int num1, int num2, int operator, CalculatorListener listener) {
            this.num1 = num1;
            this.num2 = num2;
            this.operator = operator;
            this.listener = listener;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            Socket socket = null;
            try {

                socket = new Socket(HOST, PORT);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                outputStream.write(operator);
                byte[] buffer = new byte[4];
                ByteBuffer.wrap(buffer).putInt(num1);
                outputStream.write(buffer);
                ByteBuffer.wrap(buffer).putInt(num2);
                outputStream.write(buffer);
                if(inputStream.read(buffer) != 4){
                    throw new IOException("ilegal response from server");
                }
                int result = ByteBuffer.wrap(buffer).getInt();
                if (listener != null) {
                    listener.onResult(result);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
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
                if(socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public interface CalculatorListener{
        void onResult(int result);
    }

}