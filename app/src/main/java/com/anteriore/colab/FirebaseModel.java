package com.anteriore.colab;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseModel {
    private DatabaseReference mDatabase;

    public FirebaseModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}
