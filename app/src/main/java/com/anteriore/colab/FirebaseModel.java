package com.anteriore.colab;

/**
 * Created by Seaver on 9/6/2016.
 */

import com.google.firebase

public class FirebaseModel {
    private DatabaseReference mDatabase;

    public FirebaseModel()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}
