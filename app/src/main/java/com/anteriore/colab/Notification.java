package com.anteriore.colab;

public class Notification {

    private int notificationImg;
    private String notificationText;

    public Notification(int notificationImg, String notificationText) {
        this.notificationImg = notificationImg;
        this.notificationText = notificationText;
    }

    public Notification() {
    }

    public int getNotificationImg() {
        return notificationImg;
    }

    public void setNotificationImg(int notificationImg) {
        this.notificationImg = notificationImg;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}
