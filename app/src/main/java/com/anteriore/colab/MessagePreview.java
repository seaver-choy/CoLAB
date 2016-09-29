package com.anteriore.colab;

public class MessagePreview {

    private String messagePerson;
    private String lastMessage;
    private String timeStamp;
    private int imageURL;

    public MessagePreview(String messagePerson, String lastMessage, String timeStamp, int imageURL) {
        this.messagePerson = messagePerson;
        this.lastMessage = lastMessage;
        this.imageURL = imageURL;
        this.timeStamp = timeStamp;

    }

    public String getMessagePerson() {
        return messagePerson;
    }

    public void setMessagePerson(String messagePerson) {
        this.messagePerson = messagePerson;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
