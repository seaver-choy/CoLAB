package com.anteriore.colab;

public class MessageItem {

    private int messageID;
    private String message;
    private String sender;

    public MessageItem(int messageID, String message, String sender) {
        this.messageID = messageID;
        this.message = message;
        this.sender = sender;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
