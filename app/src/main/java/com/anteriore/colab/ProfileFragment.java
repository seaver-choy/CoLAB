package com.anteriore.colab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profilePosition;
    private TextView quote;
    private Button settingsButton;
    private RelativeLayout connectionsTab;
    private RelativeLayout interestsTab;
    private RecyclerView connectionsRecyclerView;
    private RecyclerView interestsRecyclerView;
    private ProfileConnectionAdapter profileConnectionAdapter;
    private ProfileInterestAdapter interestAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.profile_view, container, false);

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

        connectionsRecyclerView = (RecyclerView) v.findViewById(R.id.profile_recyclerview);
        interestsRecyclerView = (RecyclerView) v.findViewById(R.id.profile_card_recyclerview);
        profileImage = (ImageView) v.findViewById(R.id.profile_photo);
        profileName = (TextView) v.findViewById(R.id.profile_name);
        profilePosition = (TextView) v.findViewById(R.id.profile_field);
        quote = (TextView) v.findViewById(R.id.profile_quote);
        settingsButton = (Button) v.findViewById(R.id.profile_settings_button);
        connectionsTab = (RelativeLayout) v.findViewById(R.id.profile_connections_button);
        interestsTab = (RelativeLayout) v.findViewById(R.id.profile_common_interest_button);

        profileConnectionAdapter = new ProfileConnectionAdapter(getContext(), connections);
        connectionsRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        connectionsRecyclerView.setAdapter(profileConnectionAdapter);

        interestAdapter = new ProfileInterestAdapter(interests);
        interestsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        interestsRecyclerView.setAdapter(interestAdapter);

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

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
