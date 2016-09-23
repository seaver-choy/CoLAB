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

        fbModel = FirebaseModel.getInstance(getApplicationContext());
        final ArrayList<Interest> likeList = fbModel.getLikeList();
        final ArrayList<Interest> passionList = fbModel.getPassionList();
        final ArrayList<Interest> hobbyList = fbModel.getHobbyList();
        likesRecyclerView = (RecyclerView) findViewById(R.id.likes_recyclerview);
        hobbiesRecyclerView = (RecyclerView) findViewById(R.id.hobbies_recyclerview);
        passionRecyclerView = (RecyclerView) findViewById(R.id.passion_recyclerview);

        skipStep = (TextView) findViewById(R.id.skip_step_text);

        skipStep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterestActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final InterestAdapter likeAdapter = new InterestAdapter(likeList);
        final InterestAdapter passionAdapter = new InterestAdapter(passionList);
        final InterestAdapter hobbyAdapter = new InterestAdapter(hobbyList);

        likesRecyclerView.setAdapter(likeAdapter);
        hobbiesRecyclerView.setAdapter(hobbyAdapter);
        passionRecyclerView.setAdapter(passionAdapter);

        likesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        hobbiesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        passionRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

    }
}
