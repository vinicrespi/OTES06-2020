package com.vinicius.chatandroid.models;

public class Message {
    public String content;
    public String sender;
    public String receiver;
    public String address;
    public String status;
    public boolean error;

    public Message(String content, String sender, String receiver, String address, String status, boolean error) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.status = status;
        this.error = error;
    }
}
