package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hackeru on 22 נוב 18.
 */
public class User {
    private String userName, password;

    public User(InputStream inputStream) throws IOException {
        int userNameLength = inputStream.read();
        byte[] userNameBytes = new byte[userNameLength];
        int actuallyRead;
        actuallyRead = inputStream.read(userNameBytes);
        if(actuallyRead != userNameLength)
            throw new IOException("big balgan");
        this.userName = new String(userNameBytes);
        int passwordLength = inputStream.read();
        byte[] passwordBytes = new byte[passwordLength];
        actuallyRead = inputStream.read(passwordBytes);
        if(actuallyRead != passwordLength)
            throw new IOException("big balagan");
        this.password = new String(passwordBytes);

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public void write(OutputStream outputStream) throws IOException{
        byte[] userNameBytes = userName.getBytes();
        outputStream.write(userNameBytes.length);
        outputStream.write(userNameBytes);

        byte[] passwordBytes = password.getBytes();
        outputStream.write(passwordBytes.length);
        outputStream.write(passwordBytes);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}