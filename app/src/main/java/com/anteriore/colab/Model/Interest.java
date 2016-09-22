package com.anteriore.colab.Model;

import java.util.ArrayList;

public class Interest {
    private String interestName;
    private String interestImage;
    private String interestID;
    private interestTypes interestType;
    private int interestImageResource;
    private int currLikes;
    public static String interestNameConstant = "interestName";
    public static String interestTypeConstant = "interestType";
    public static String TABLE_NAME = "Interest";
    public static String COLUMN_NAME = "interest_name";
    public static String COLUMN_IMAGE = "interest_image";
    public static String COLUMN_ID = "interestID";
    public static String COLUMN_TYPE = "interest_type";
    public static String FirebaseChildName = "interests";
    public enum interestTypes {
        hobby, like, passion
    }

    public Interest() {
    }

    public Interest(String interestName, String interestImage) {
        this.interestName = interestName;
        this.interestImage = interestImage;
        this.currLikes = 0;
    }

    public Interest(String interestID , String interestName, String interestImage) {
        this.interestName = interestName;
        this.interestImage = interestImage;
        this.interestID = interestID;
        this.currLikes = 0;
    }

    public interestTypes getInterestType() {
        return interestType;
    }

    public void setInterestType(interestTypes interestType) {
        this.interestType = interestType;
    }

    public int getInterestImageResource() {
        return interestImageResource;
    }

    public void setInterestImageResource(int interestImageResource) {
        this.interestImageResource = interestImageResource;
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

    public String getInterestImage() {
        return interestImage;
    }

    public void setInterestImage(String interestImage) {
        this.interestImage = interestImage;
    }
}
