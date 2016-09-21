package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profilePosition;
    private TextView quote;
    private Button backButton;
    private Button connectButton;
    private RecyclerView connectionsRecyclerView;
    private ProfileConnectionAdapter profileConnectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final ArrayList<ProfileConnection> connections = new ArrayList<>();

        connections.add(new ProfileConnection("Chino Tapales", "10 connections", "26 common interests", R.drawable.profile_chino));
        connections.add(new ProfileConnection("Chris Angping", "12 connections", "31 common interestes", R.drawable.profile_chris));
        connections.add(new ProfileConnection("David Gamboa", "14 connections", "52 common interestes", R.drawable.profile_david));
        connections.add(new ProfileConnection("Seaver Choy", "16 connections", "12 common interests", R.drawable.profile_seaver));

        connectionsRecyclerView = (RecyclerView) findViewById(R.id.profile_activity_recyclerview);
        profileImage = (ImageView) findViewById(R.id.profile_activity_photo);
        profileName = (TextView) findViewById(R.id.profile_activity_name);
        profilePosition = (TextView) findViewById(R.id.profile_activity_field);
        quote = (TextView) findViewById(R.id.profile_activity_quote);
        backButton = (Button) findViewById(R.id.profile_activity_back_button);
        connectButton = (Button) findViewById(R.id.profile_activity_connect_button);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        profileConnectionAdapter = new ProfileConnectionAdapter(this, connections);
        connectionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectionsRecyclerView.setAdapter(profileConnectionAdapter);
    }
}
