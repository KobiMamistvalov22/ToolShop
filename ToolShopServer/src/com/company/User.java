package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class User {
    private String userName , passWord;
//---Getting from client side the User
    public User(InputStream inputStream) throws IOException {
        int userNameLength = inputStream.read();
        byte[] userNameByte = new byte[userNameLength];
        int actuallyRead;
        actuallyRead = inputStream.read(userNameByte);
        if (actuallyRead != userNameLength){
            throw new IOException("F...!!! ");
        }
        this.userName = new String(userNameByte);
        int passLength = inputStream.read();
        byte[] passByte = new byte[passLength];
        actuallyRead = inputStream.read(passByte);
        if(actuallyRead != passLength){
            throw new IOException("S...!");
        }
        this.passWord = new String(passByte);
    }

    public void write(OutputStream outputStream) throws IOException {
        byte[] userNameBytes = userName.getBytes();
        outputStream.write(userNameBytes.length);
        outputStream.write(userNameBytes);
        byte[] passWordBytes = passWord.getBytes();
        outputStream.write(passWordBytes.length);
        outputStream.write(passWordBytes);
    }
    public User(String userName, String password){
        this.userName = userName;
        this.passWord = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord) {

        this.passWord = passWord;
    }

}
