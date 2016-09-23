package com.anteriore.colab;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anteriore.colab.Model.Interest;
import com.anteriore.colab.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profilePosition;
    private TextView quote;
    private TextView profileCardConnectionText, profileCardInterestText;
    private Button backButton;
    private Button connectButton;
    private RelativeLayout connectionsTab;
    private RelativeLayout interestsTab;
    private RecyclerView connectionsRecyclerView;
    private RecyclerView interestsRecyclerView;
    private ProfileConnectionAdapter profileConnectionAdapter;
    private ProfileInterestAdapter interestAdapter;
    private FirebaseModel fbModel;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        currentUser = (User) getIntent().getSerializableExtra("selectedUser");

        fbModel = FirebaseModel.getInstance(getApplicationContext());


        final ArrayList<User> users = new ArrayList<>();
        /*
        connections.add(new ProfileConnection("Chino Tapales", "10 connections", "26 common interests", R.drawable.profile_chino));
        connections.add(new ProfileConnection("Chris Angping", "12 connections", "31 common interestes", R.drawable.profile_chris));
        connections.add(new ProfileConnection("David Gamboa", "14 connections", "52 common interestes", R.drawable.profile_david));
        connections.add(new ProfileConnection("Seaver Choy", "16 connections", "12 common interests", R.drawable.profile_seaver));
        */

        final List<Interest> interests = new ArrayList<>();

        interestAdapter = new ProfileInterestAdapter(interests);
        /*
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
        */

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currCard = dataSnapshot.getValue(Interest.class);

                    Resources resources = getApplication().getApplicationContext().getResources();
                    final int resourceId = resources.getIdentifier(currCard.getInterestImage(), "drawable",
                            getApplication().getApplicationContext().getPackageName());

                    currCard.setInterestImageResource(resourceId);
                    interests.add(currCard);
                }
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

        fbModel.getmDatabase().child("colab").child(User.FirebaseChildName).child(currentUser.getUserID()).child(User.FirebaseInterestList).addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        connectionsRecyclerView = (RecyclerView) findViewById(R.id.profile_activity_recyclerview);
        interestsRecyclerView = (RecyclerView) findViewById(R.id.profile_activity_card_recyclerview);
        profileImage = (ImageView) findViewById(R.id.profile_activity_photo);
        profileImage.setImageResource(currentUser.getProfilePictureResource());
        profileName = (TextView) findViewById(R.id.profile_activity_name);
        profileName.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        profilePosition = (TextView) findViewById(R.id.profile_activity_field);
        profilePosition.setText("");
        quote = (TextView) findViewById(R.id.profile_activity_quote);
        quote.setText("");
        backButton = (Button) findViewById(R.id.profile_activity_back_button);
        connectButton = (Button) findViewById(R.id.profile_activity_connect_button);
        connectionsTab = (RelativeLayout) findViewById(R.id.profile_connections_button);
        profileCardConnectionText = (TextView) findViewById(R.id.profile_card_connection_text);
        profileCardConnectionText.setText(String.valueOf(currentUser.getNumberOfFriends()));
        interestsTab = (RelativeLayout) findViewById(R.id.profile_common_interest_button);
        profileCardInterestText = (TextView) findViewById(R.id.profile_card_interest_text);
        profileCardInterestText.setText(String.valueOf(currentUser.getNumberOfInterests()));

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbModel.getUserFromDatabase(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(User.FirebaseFriendList).push().setValue(currentUser);
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

        profileConnectionAdapter = new ProfileConnectionAdapter(this, users);
        connectionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectionsRecyclerView.setAdapter(profileConnectionAdapter);

        interestsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        interestsRecyclerView.setAdapter(interestAdapter);
    }
}
