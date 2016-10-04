package com.anteriore.colab.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Seaver on 9/3/2016.
 */
public class User implements Serializable{

    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String userID;
    private int profilePictureResource;
    private String profilePictureName;
    private ArrayList<Interest> currInterests;
    private ArrayList<Notification> currNotifications;
    private ArrayList<User> friendList;

    public static String FirebaseChildName = "users";
    public static String FirebaseFriendList = "friendList";
    public static String FirebaseInterestList = "currInterests";
    public static String FirebaseNotificationList = "currNotifications";
    public static String FirebaseMessagingList = "currMessages";
    public static String TABLE_NAME = "users";
    public static String COLUMN_FIRST_NAME = "firstname";
    public static String COLUMN_LAST_NAME = "lastname";
    public static String COLUMN_EMAIL = "email";

    public User() {
    }

    public User(String userID){
        this.userID = userID;
    }

    public User(String userID, String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userID = userID;
        currInterests = new ArrayList<>();
        friendList = new ArrayList<>();
    }

    /*
    public ArrayList<Notification> getCurrNotifications() {
        return currNotifications;
    }
    */

    public ArrayList<Notification> getNotificationList(){
        return currNotifications;
    }

    public void setCurrNotifications(ArrayList<Notification> currNotifications) {
        this.currNotifications = currNotifications;
    }

    public int getProfilePictureResource() {
        return profilePictureResource;
    }

    public void setProfilePictureResource(int profilePictureResource) {
        this.profilePictureResource = profilePictureResource;
    }

    public String getProfilePictureName() {
        return profilePictureName;
    }

    public void setProfilePictureName(String profilePictureName) {
        this.profilePictureName = profilePictureName;
    }

    /*
    public ArrayList<Interest> getCurrInterests() {
        return currInterests;
    }
    */

    public ArrayList<Interest> getListOfInterests(){
        return currInterests;
    }

    public void setCurrInterests(ArrayList<Interest> currInterests) {
        this.currInterests = currInterests;
    }

    public int getNumberOfInterests(){
        if(currInterests == null)
        {
            return 0;
        }
        return currInterests.size();
    }
    /*
    public ArrayList<User> getFriendList() {
        return friendList;
    }
    */

    public ArrayList<User> getListOfFriends(){
        return friendList;
    }

    public void setFriendList(ArrayList<User> friendList) {
        this.friendList = friendList;
    }

    public int getNumberOfFriends(){
        if(friendList == null)
        {
            return 0;
        }
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

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof User)
        {
            User user = (User) o;
            if(user.getUserID().equalsIgnoreCase(this.getUserID()))
            {
                return true;
            }
        }
        return false;
    }
}
