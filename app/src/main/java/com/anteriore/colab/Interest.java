package com.anteriore.colab;

public class Interest {
    private String interestName;
    private int interestImage;

    public Interest() {
    }

    public Interest(String interestName, int interestImage) {
        this.interestName = interestName;
        this.interestImage = interestImage;
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
