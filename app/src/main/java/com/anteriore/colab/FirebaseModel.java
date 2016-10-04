package com.anteriore.colab;

/**
 * Created by Seaver on 9/6/2016.
 */

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.anteriore.colab.Model.Hobby;
import com.anteriore.colab.Model.Interest;
import com.anteriore.colab.Model.Like;
import com.anteriore.colab.Model.Notification;
import com.anteriore.colab.Model.Passion;
import com.anteriore.colab.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;

public class FirebaseModel {
    private DatabaseReference mDatabase;
    private ArrayList<User> userList;
    private ArrayList<Interest> likeList;
    private ArrayList<Interest> hobbyList;
    private ArrayList<Interest> passionList;
    private ArrayList<Notification> notificationList;
    private User currentUser;
    private Context context;

    private static FirebaseModel instance;

    public static FirebaseModel getInstance(Context contextFromActivity)
    {
        if(instance == null)
        {
            instance = new FirebaseModel(contextFromActivity);
        }
        return instance;
    }

    private FirebaseModel(Context contextFromActivity) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.context = contextFromActivity;
        this.likeList = new ArrayList();
        this.hobbyList = new ArrayList();
        this.passionList = new ArrayList();
        this.userList = new ArrayList();
        this.notificationList = new ArrayList();

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currLike = dataSnapshot.getValue(Interest.class);

                    Resources resources = context.getResources();
                    final int resourceId = resources.getIdentifier(currLike.getInterestImage(), "drawable",
                            context.getPackageName());

                    currLike.setInterestType(Interest.interestTypes.like);
                    currLike.setInterestID(dataSnapshot.getKey());
                    currLike.setInterestImageResource(resourceId);
                    likeList.add(currLike);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.child("colab").child(Interest.FirebaseChildName).child(Like.FirebaseChildName).addChildEventListener(CEL);

        CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currHobby = dataSnapshot.getValue(Interest.class);

                    Resources resources = context.getResources();
                    final int resourceId = resources.getIdentifier(currHobby.getInterestImage(), "drawable",
                            context.getPackageName());

                    currHobby.setInterestType(Interest.interestTypes.hobby);
                    currHobby.setInterestID(dataSnapshot.getKey());
                    currHobby.setInterestImageResource(resourceId);
                    hobbyList.add(currHobby);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.child("colab").child(Interest.FirebaseChildName).child(Hobby.FirebaseChildName).addChildEventListener(CEL);

        CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currPassion = dataSnapshot.getValue(Interest.class);

                    Resources resources = context.getResources();
                    final int resourceId = resources.getIdentifier(currPassion.getInterestImage(), "drawable",
                            context.getPackageName());

                    currPassion.setInterestType(Interest.interestTypes.passion);
                    currPassion.setInterestID(dataSnapshot.getKey());
                    currPassion.setInterestImageResource(resourceId);
                    passionList.add(currPassion);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.child("colab").child(Interest.FirebaseChildName).child(Passion.FirebaseChildName).addChildEventListener(CEL);
    }

    public void writeNotificationToUser(Notification notification, User user){
        notification.setNotificationText(currentUser.getFirstName() + " " + currentUser.getLastName() + " wants to connect with you!");
        notification.setFromUserID(currentUser.getUserID());
        DatabaseReference reference = mDatabase.child("colab").child(User.FirebaseChildName).child(user.getUserID()).child(User.FirebaseNotificationList);
        String key = reference.push().getKey();
        notification.setNotificationID(key);
        reference.child(key).setValue(notification);
    }

    public void removeNotificationFromUser(Notification notification){
        mDatabase.child("colab").child(User.FirebaseChildName).child(currentUser.getUserID()).child(User.FirebaseNotificationList).child(notification.getNotificationID()).removeValue();
    }

    public void addAsFriendsThroughNotification(Notification notification){
        User user = new User(notification.getFromUserID());
        if(userList.contains(user))
        {
            Log.e("HELLO", "I GOT IN.");
            user = userList.get(userList.indexOf(user));
            mDatabase.child("colab").child(User.FirebaseChildName).child(currentUser.getUserID()).child(User.FirebaseFriendList).push().setValue(user);
            mDatabase.child("colab").child(User.FirebaseChildName).child(user.getUserID()).child(User.FirebaseFriendList).push().setValue(currentUser);
        }
    }
    public void loadUsers(){
        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                Log.e("FirebaseAuth", FirebaseAuth.getInstance().getCurrentUser().getUid());

                if(!FirebaseAuth.getInstance().getCurrentUser().getUid().equalsIgnoreCase(dataSnapshot.getKey())) {
                    User currUser = dataSnapshot.getValue(User.class);

                    if(dataSnapshot.hasChild(User.FirebaseInterestList)) {
                        currUser.setCurrInterests(getInterestsListOfUser(dataSnapshot));
                    }
                    if(dataSnapshot.hasChild(User.FirebaseFriendList)) {

                        currUser.setFriendList(getFriendListOfUser(dataSnapshot));
                    }

                    Resources resources = context.getResources();
                    final int resourceId = resources.getIdentifier("profile", "drawable",
                            context.getPackageName());

                    currUser.setProfilePictureResource(resourceId);

                    Log.e("What", "Added user.");

                    userList.add(currUser);
                }else{
                    User currUser = dataSnapshot.getValue(User.class);

                    if(dataSnapshot.hasChild(User.FirebaseInterestList)) {
                        currUser.setCurrInterests(getInterestsListOfUser(dataSnapshot));
                    }
                    if(dataSnapshot.hasChild(User.FirebaseFriendList)) {

                        currUser.setFriendList(getFriendListOfUser(dataSnapshot));
                    }
                    Resources resources = context.getResources();
                    final int resourceId = resources.getIdentifier("profile", "drawable",
                            context.getPackageName());

                    currUser.setProfilePictureResource(resourceId);
                    Log.e("YEHEY", "Got the current logged in user." + currUser.getUserID());
                    currentUser = currUser;
                    currentUser.setUserID(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    loadNotifications();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.child("colab").child(User.FirebaseChildName).addChildEventListener(CEL);
    }

    private void loadNotifications(){
        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Notification notification = dataSnapshot.getValue(Notification.class);


                Resources resources = context.getResources();
                final int resourceId = resources.getIdentifier("profile", "drawable",
                        context.getPackageName());

                notification.setNotificationImage(resourceId);

                notificationList.add(notification);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.child("colab").child(User.FirebaseChildName).child(currentUser.getUserID()).child(User.FirebaseNotificationList).addChildEventListener(CEL);
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void writeInterestToDatabase(Interest interest)
    {
        mDatabase.child("colab").child("interests").child(interest.getInterestName().toLowerCase()).setValue(interest);
        Log.e("Write To Database", interest.getInterestName());
    }

    public void writeNewInterestToDatabase(Interest interest)
    {
        mDatabase.child("colab").child("interests").push().setValue(interest);
    }

    public void writeNewHobbyToDatabase(Hobby hobby)
    {
        mDatabase.child("colab").child("interests").child("hobby").push().setValue(hobby);
    }

    public void writeUserToDatabase(User user)
    {
        mDatabase.child("colab").child("users").child(user.getUserID()).setValue(user);
    }

    public int getNumberOfSimilarInterests(User user)
    {
        int noOfSimilar = 0;
        if(currentUser.getListOfInterests() != null && user.getListOfInterests() != null) {
            for (int i = 0; i < currentUser.getListOfInterests().size(); i++) {
                if (user.getListOfInterests().contains(currentUser.getListOfInterests().get(i))) {
                    noOfSimilar++;
                }
            }
        }
        return noOfSimilar;
    }

    public int getNumberOfSimilarFriends(User user)
    {
        int noOfSimilar = 0;
        if(currentUser.getListOfFriends() != null && user.getListOfFriends() != null) {
            for (int i = 0; i < currentUser.getListOfFriends().size(); i++) {
                if (user.getListOfFriends().contains(currentUser.getListOfFriends().get(i))) {
                    noOfSimilar++;
                }
            }
        }
        return noOfSimilar;
    }

    public void writeNewUserToDatabase(User user)
    {
        mDatabase.child("colab").child("users").child(user.getUserID()).setValue(user);
    }

    public DatabaseReference getUserFromDatabase(String userID)
    {
        return mDatabase.child("colab").child("users").child(userID);
    }

    private ArrayList<Interest> getInterestsListOfUser(DataSnapshot dataSnapshot)
    {
        GenericTypeIndicator<HashMap<String, Interest>> interestTemp = new GenericTypeIndicator<HashMap<String, Interest>>() {
        };
        HashMap<String, Interest> tempList = dataSnapshot.child(User.FirebaseInterestList).getValue(interestTemp);

        ArrayList<Interest> currInterests = new ArrayList();
        for (Interest value : tempList.values()) {
            currInterests.add(value);
        }

        return currInterests;
    }

    private ArrayList<User> getFriendListOfUser(DataSnapshot dataSnapshot)
    {
        GenericTypeIndicator<HashMap<String, User>> userTemp = new GenericTypeIndicator<HashMap<String, User>>() {
        };

        HashMap<String, User> userTempList = dataSnapshot.child(User.FirebaseFriendList).getValue(userTemp);

        ArrayList<User> currFriends = new ArrayList();
        for (User value : userTempList.values()) {
            currFriends.add(value);
        }

        return currFriends;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<Interest> getLikeList() {
        return likeList;
    }

    public ArrayList<Interest> getHobbyList() {
        return hobbyList;
    }

    public ArrayList<Interest> getPassionList() {
        return passionList;
    }

    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
