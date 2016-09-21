package com.anteriore.colab;

import com.anteriore.colab.Model.Interest;

import java.util.ArrayList;

/**
 * Created by Seaver on 9/3/2016.
 */
public class User {

    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String userID;
    private ArrayList<Interest> currInterests;
    private ArrayList<User> friendList;

    public User() {
    }

    public User(String userID, String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userID = userID;
        currInterests = new ArrayList<>();
        friendList = new ArrayList<>();
    }

    public ArrayList<Interest> getCurrInterests() {
        return currInterests;
    }

    public void setCurrInterests(ArrayList<Interest> currInterests) {
        this.currInterests = currInterests;
    }

    public int numberOfInterests(){
        return currInterests.size();
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<User> friendList) {
        this.friendList = friendList;
    }

    public int numberOfFriends(){
        return friendList.size();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
