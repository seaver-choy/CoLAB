package com.anteriore.colab;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.anteriore.colab.Model.Hobby;
import com.anteriore.colab.Model.Interest;
import com.anteriore.colab.Model.Like;
import com.anteriore.colab.Model.Passion;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

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
        final ArrayList<Interest> likeList = new ArrayList<>();
        final ArrayList<Interest> passionList = new ArrayList<>();
        final ArrayList<Interest> hobbyList = new ArrayList<>();
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

        final InterestAdapter likeAdapter = new InterestAdapter(likeList);
        final InterestAdapter passionAdapter = new InterestAdapter(passionList);
        final InterestAdapter hobbyAdapter = new InterestAdapter(hobbyList);

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currLike = dataSnapshot.getValue(Interest.class);

                    Resources resources = getApplication().getApplicationContext().getResources();
                    final int resourceId = resources.getIdentifier(currLike.getInterestImage(), "drawable",
                            getApplication().getApplicationContext().getPackageName());

                    currLike.setInterestImageResource(resourceId);
                    likeList.add(currLike);
                }
                likeAdapter.notifyDataSetChanged();
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

        fbModel.getmDatabase().child("colab").child("interests").child("like").addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currHobby = dataSnapshot.getValue(Interest.class);

                    Resources resources = getResources();
                    final int resourceId = resources.getIdentifier(currHobby.getInterestImage(), "drawable",
                            getPackageName());

                    currHobby.setInterestImageResource(resourceId);
                    hobbyList.add(currHobby);
                }
                hobbyAdapter.notifyDataSetChanged();
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

        fbModel.getmDatabase().child("colab").child("interests").child("hobby").addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currPassion = dataSnapshot.getValue(Interest.class);

                    Resources resources = getResources();
                    final int resourceId = resources.getIdentifier(currPassion.getInterestImage(), "drawable",
                            getPackageName());

                    currPassion.setInterestImageResource(resourceId);
                    passionList.add(currPassion);
                }
                passionAdapter.notifyDataSetChanged();
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

        fbModel.getmDatabase().child("colab").child("interests").child("passion").addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        likesRecyclerView.setAdapter(likeAdapter);
        hobbiesRecyclerView.setAdapter(hobbyAdapter);
        passionRecyclerView.setAdapter(passionAdapter);

        likesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        hobbiesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        passionRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

    }
}
