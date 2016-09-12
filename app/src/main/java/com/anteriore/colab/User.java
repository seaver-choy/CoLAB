package com.anteriore.colab;

/**
 * Created by Seaver on 9/3/2016.
 */
public class User {

    private String fullName;
    private String birthday;
    private String email;
    private String userID;

    public User(String fullName, String birthday, String email) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public User() {
    }

    public User(String fullName, String birthday, String email, String userID) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
