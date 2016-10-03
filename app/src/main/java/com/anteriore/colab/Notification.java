package com.anteriore.colab;

import java.io.Serializable;

public class Notification implements Serializable{

    private int notificationImage;
    private String notificationImageName;
    private String notificationID;
    private String notificationText;
    private String fromUserID;

    public Notification(int notificationImage, String notificationText) {
        this.notificationImage = notificationImage;
        this.notificationText = notificationText;
    }

    public Notification(String notificationImageName, String notificationText)
    {
        this.notificationImageName = notificationImageName;
        this.notificationText = notificationText;
    }

    public Notification(String notificationImageName)
    {
        this.notificationImageName = notificationImageName;
    }

    public Notification() {
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(int notificationImage) {
        this.notificationImage = notificationImage;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}
