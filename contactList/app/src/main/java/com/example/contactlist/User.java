package com.example.contactlist;

public class User {
    String name,lastMessage,lastMessageTime,phoneNo,country;
    int imageId;

    public User(String name, String lastMessage, String lastMessageTime, String phoneNo, String country, int imageId) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.phoneNo = phoneNo;
        this.country = country;
        this.imageId = imageId;
    }
}
