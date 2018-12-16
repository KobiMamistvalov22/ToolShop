package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientThread extends Thread{
    public final int SIGNUP = 100;
    public final int LOGIN = 101;
    public final int OK = 200;
    public final int FAILURE = 201;
    public final int GET_TOOLS = 250;
    private Socket socket;

    private Map<String, User> users;
    private Tools tools;
    private InputStream inputStream;
    private OutputStream outputStream;
    //---Here the client connecting with the server

    public ClientThread(Socket socket, Map<String, User> users) {
        this.socket = socket;
        this.users = users;
        this.tools = new Tools();
    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            int action = inputStream.read();
            switch (action){
                case SIGNUP:
                    signup();
                    break;
                case LOGIN:
                    login();
                    break;
                case GET_TOOLS:
                    sendTools();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
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
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void signup() throws IOException{
        User user = new User(inputStream);
        boolean successSignup = false;
        if (!users.containsKey(user.getUserName())){
            users.put(user.getUserName(), user);

            // Add to usersFile name,pass
            BufferedWriter usersFile = new BufferedWriter(new FileWriter("usersFile.txt", true));

            PrintWriter printWriter = new PrintWriter(usersFile);
            printWriter.println(user.getUserName() + "," + user.getPassWord());
            printWriter.close();

            successSignup = true;
        }
        outputStream.write(successSignup ? OK : FAILURE);
    }
    private void login() throws IOException{
        User user = new User(inputStream);

        outputStream.write(validateUser(user) ? OK : FAILURE);
    }
    private boolean validateUser(User user){
        User existUser = users.get(user.getUserName());
        if(existUser != null){
            if(user.getPassWord().equals(existUser.getPassWord())){
                return true;
            }
        }
        return false;
    }

    private void sendTools() throws IOException {
        this.tools.write(outputStream);
    }
}



