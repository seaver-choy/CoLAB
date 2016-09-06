package com.anteriore.colab;

public class Interest {
    private String interestName;
    private int interestImage;
    private String interestID;
    public static String TABLE_NAME = "Interest";

    public Interest() {
    }

    public Interest(String interestName, int interestImage) {
        this.interestName = interestName;
        this.interestImage = interestImage;
    }

    public Interest(String interestName, int interestImage, String interestID) {
        this.interestName = interestName;
        this.interestImage = interestImage;
        this.interestID = interestID;
    }

    public Interest(String interestName) {
        this.interestName = interestName;
    }

    public String getInterestID() {
        return interestID;
    }

    public void setInterestID(String interestID) {
        this.interestID = interestID;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public int getInterestImage() {
        return interestImage;
    }

    public void setInterestImage(int interestImage) {
        this.interestImage = interestImage;
    }
}
