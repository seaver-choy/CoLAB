package com.anteriore.colab;

/**
 * Created by Seaver on 9/6/2016.
 */

import android.util.Log;

import com.anteriore.colab.Model.Hobby;
import com.anteriore.colab.Model.Interest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseModel {
    private DatabaseReference mDatabase;

    public FirebaseModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
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

    public void writeNewUserToDatabase(User user)
    {
        mDatabase.child("colab").child("users").push().setValue(user);
    }
}
