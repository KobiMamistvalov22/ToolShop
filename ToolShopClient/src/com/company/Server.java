package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Server {


    public static final String HOST = "127.0.0.1";
    public static final int PORT = 3000;
    public static final int SIGNUP = 100;
    public static final int LOGIN = 101;
    public static final int OK = 200;
    public static GetToolsThread toolsThread;

    public static User user;
    public static boolean login(String userName, String passWord, boolean isSignup) {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //---Here the client connecting with the server
        try {
            socket = new Socket(HOST, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write(isSignup ? SIGNUP : LOGIN);
            user = new User(userName, passWord);
            user.write(outputStream);
            int response = inputStream.read();
            if (response == OK) {//Go to GetToolsThread
                toolsThread = new GetToolsThread();
                toolsThread.run();
                return true;
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
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
        }
        return false;
    }
}
