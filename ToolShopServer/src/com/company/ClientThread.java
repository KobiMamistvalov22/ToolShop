package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientThread extends Thread{

    public static final int GET_TOOLS = 300;
    public static final int SEND_TOOLS = 301;
    public final int SIGNUP = 100;
    public final int LOGIN = 101;
    public final int OK = 200;
    public final int FAILURE = 201;
    private Socket socket;
    private ArrayList<ToolsStore> toolsStores;

    private Map<String, User> users;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket socket, ArrayList<ToolsStore> toolsStores, Map<String, User> users) {
        this.socket = socket;
        this.toolsStores = toolsStores;
        this.users = users;
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
                    getTools();
                    break;
                case SEND_TOOLS:
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

            successSignup = true;
        }
        outputStream.write(successSignup ? OK : FAILURE);
    }
    private void login() throws IOException{
        User user = new User(inputStream);
        outputStream.write(valuUser(user) ? OK : FAILURE);
    }
    private boolean valuUser(User user){
        User existUser = users.get(user.getUserName());
        if(existUser != null){
            if(user.getPassWord().equals(existUser.getPassWord())){
                return true;
            }
        }
        return false;
    }

    private void sendTools() throws IOException {
        User user = new User(inputStream);
        if (!valuUser(user))
            return;
        int toolsStoreLength = inputStream.read();
        if (toolsStoreLength == -1)
            return;
        byte[] toolsStoreBytes = new byte[toolsStoreLength];
        int actuallyRead = inputStream.read(toolsStoreBytes);
        if (actuallyRead != toolsStoreLength)
            return;
        ToolsStore toolsStore = new ToolsStore(user.getUserName(), new Integer(String.valueOf(toolsStoreBytes)));
        toolsStores.add(toolsStore);
        outputStream.write(OK);
    }

    private void getMessages() throws IOException{
        User user = new User(inputStream);
        if(!valuUser(user))
            return;
        byte[] fromBytes = new byte[4];
        int actuallyRead = inputStream.read(fromBytes);
        if(actuallyRead != 4)
            return;
        int from = ByteBuffer.wrap(fromBytes).getInt();
        for (int i = from; i < toolsStores.size(); i++) {
            ToolsStore toolsStore = toolsStores.get(i);
            toolsStore.write(outputStream);
        }
    }

    private void getTools(){

    }
}



