package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    private RecyclerView interestRecyclerView;
    private FirebaseModel fbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        interestRecyclerView = (RecyclerView) findViewById(R.id.interest_recyclerview);
        fbModel = new FirebaseModel();
        final ArrayList<Interest> interestList = new ArrayList<>();

        final InterestAdapter interestAdapter = new InterestAdapter(interestList);

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                interestList.add(dataSnapshot.getValue(Interest.class));
                interestAdapter.notifyDataSetChanged();
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

        fbModel.getmDatabase().child("colab").child("interests").addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);


        interestRecyclerView.setAdapter(interestAdapter);

        interestRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }
}
