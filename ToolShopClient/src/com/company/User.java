package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class User {

    private String userName, passWord;
    public User(InputStream inputStream) throws IOException {
        int userNameLength = inputStream.read();
        byte[] userNameByte = new byte[userNameLength];
        int actuallyRead;
        actuallyRead = inputStream.read(userNameByte);
        if(actuallyRead != userNameLength){
            throw new IOException("mnv");
        }
        this.userName = new String(userNameByte);
        //pass
        int passWordLength = inputStream.read();
        byte[] passWordByte = new byte[passWordLength];
        actuallyRead = inputStream.read(passWordByte);
        if (actuallyRead != passWordLength){
            throw new IOException("xfgn");
        }
        this.passWord =new String(passWordByte);
    }

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
    public void write(OutputStream outputStream) throws IOException{
        byte[] userNameByte = userName.getBytes();
        outputStream.write(userNameByte.length);
        outputStream.write(userNameByte);

        byte[] passWordByte = passWord.getBytes();
        outputStream.write(passWordByte.length);
        outputStream.write(passWordByte);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
