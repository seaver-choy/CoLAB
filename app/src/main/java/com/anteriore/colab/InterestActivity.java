package com.anteriore.colab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    private RecyclerView interestRecyclerView;
    private FirebaseModel fbModel;
    private RecyclerView likesRecyclerView;
    private RecyclerView hobbiesRecyclerView;
    private RecyclerView passionRecyclerView;
    private TextView skipStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        fbModel = new FirebaseModel();
        final ArrayList<Interest> interestList = new ArrayList<>();
        likesRecyclerView = (RecyclerView) findViewById(R.id.likes_recyclerview);
        hobbiesRecyclerView = (RecyclerView) findViewById(R.id.hobbies_recyclerview);
        passionRecyclerView = (RecyclerView) findViewById(R.id.passion_recyclerview);

        skipStep = (TextView) findViewById(R.id.skip_step_text);

        skipStep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterestActivity.this, CardActivity.class);
                startActivity(intent);
                finish();
            }
        });

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

        likesRecyclerView.setAdapter(interestAdapter);
        hobbiesRecyclerView.setAdapter(interestAdapter);
        passionRecyclerView.setAdapter(interestAdapter);

        likesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        hobbiesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        passionRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

    }
}
