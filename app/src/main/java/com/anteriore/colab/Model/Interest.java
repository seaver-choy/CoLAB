package com.anteriore.colab.Model;

import java.util.ArrayList;

public class Interest {
    private String interestName;
    private int interestImage;
    private String interestID;
    private int currLikes;
    public static String TABLE_NAME = "Interest";
    public static String COLUMN_NAME = "interest_name";
    public static String COLUMN_IMAGE = "interest_image";
    public static String COLUMN_ID = "interestID";


    public Interest() {
    }

    public Interest(String interestName, int interestImage) {
        this.interestName = interestName;
        this.interestImage = interestImage;
        this.currLikes = 0;
    }

    public Interest(String interestID , String interestName, int interestImage) {
        this.interestName = interestName;
        this.interestImage = interestImage;
        this.interestID = interestID;
        this.currLikes = 0;
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
