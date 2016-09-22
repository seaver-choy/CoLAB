package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anteriore.colab.Model.Interest;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profilePosition;
    private TextView quote;
    private Button backButton;
    private Button connectButton;
    private RelativeLayout connectionsTab;
    private RelativeLayout interestsTab;
    private RecyclerView connectionsRecyclerView;
    private RecyclerView interestsRecyclerView;
    private ProfileConnectionAdapter profileConnectionAdapter;
    private ProfileInterestAdapter interestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final ArrayList<ProfileConnection> connections = new ArrayList<>();

        connections.add(new ProfileConnection("Chino Tapales", "10 connections", "26 common interests", R.drawable.profile_chino));
        connections.add(new ProfileConnection("Chris Angping", "12 connections", "31 common interestes", R.drawable.profile_chris));
        connections.add(new ProfileConnection("David Gamboa", "14 connections", "52 common interestes", R.drawable.profile_david));
        connections.add(new ProfileConnection("Seaver Choy", "16 connections", "12 common interests", R.drawable.profile_seaver));

        final List<ProfileInterest> interests = new ArrayList<>();

        interests.add(new ProfileInterest("ACTING", R.drawable.interest_hobbies));
        interests.add(new ProfileInterest("DANCING", R.drawable.interest_ideologies));
        interests.add(new ProfileInterest("PAINTING", R.drawable.interest_likes));
        interests.add(new ProfileInterest("ACTING", R.drawable.interest_hobbies));
        interests.add(new ProfileInterest("DANCING", R.drawable.interest_ideologies));
        interests.add(new ProfileInterest("PAINTING", R.drawable.interest_likes));
        interests.add(new ProfileInterest("ACTING", R.drawable.interest_hobbies));
        interests.add(new ProfileInterest("DANCING", R.drawable.interest_ideologies));
        interests.add(new ProfileInterest("PAINTING", R.drawable.interest_likes));
        interests.add(new ProfileInterest("ACTING", R.drawable.interest_hobbies));
        interests.add(new ProfileInterest("DANCING", R.drawable.interest_ideologies));
        interests.add(new ProfileInterest("PAINTING", R.drawable.interest_likes));
        interests.add(new ProfileInterest("ACTING", R.drawable.interest_hobbies));
        interests.add(new ProfileInterest("DANCING", R.drawable.interest_ideologies));
        interests.add(new ProfileInterest("PAINTING", R.drawable.interest_likes));

        connectionsRecyclerView = (RecyclerView) findViewById(R.id.profile_activity_recyclerview);
        interestsRecyclerView = (RecyclerView) findViewById(R.id.profile_activity_card_recyclerview);
        profileImage = (ImageView) findViewById(R.id.profile_activity_photo);
        profileName = (TextView) findViewById(R.id.profile_activity_name);
        profilePosition = (TextView) findViewById(R.id.profile_activity_field);
        quote = (TextView) findViewById(R.id.profile_activity_quote);
        backButton = (Button) findViewById(R.id.profile_activity_back_button);
        connectButton = (Button) findViewById(R.id.profile_activity_connect_button);
        connectionsTab = (RelativeLayout) findViewById(R.id.profile_connections_button);
        interestsTab = (RelativeLayout) findViewById(R.id.profile_common_interest_button);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        connectionsTab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                interestsRecyclerView.setVisibility(View.GONE);
                connectionsRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        interestsTab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                interestsRecyclerView.setVisibility(View.VISIBLE);
                connectionsRecyclerView.setVisibility(View.GONE);
            }
        });

        profileConnectionAdapter = new ProfileConnectionAdapter(this, connections);
        connectionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectionsRecyclerView.setAdapter(profileConnectionAdapter);

        interestAdapter = new ProfileInterestAdapter(interests);
        interestsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        interestsRecyclerView.setAdapter(interestAdapter);
    }
}
