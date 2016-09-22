package com.anteriore.colab;

public class ProfileInterest {

    private String interestTitle;
    private int interestImg;

    public ProfileInterest(String interestTitle, int interestImg) {
        this.interestTitle = interestTitle;
        this.interestImg = interestImg;
    }

    public String getInterestTitle() {
        return interestTitle;
    }

    public void setInterestTitle(String interestTitle) {
        this.interestTitle = interestTitle;
    }

    public int getInterestImg() {
        return interestImg;
    }

    public void setInterestImg(int interestImg) {
        this.interestImg = interestImg;
    }
}
