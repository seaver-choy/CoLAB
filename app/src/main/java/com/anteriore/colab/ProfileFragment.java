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

import com.anteriore.colab.Model.Interest;
import com.anteriore.colab.Model.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profilePosition;
    private TextView quote;
    private TextView profileActivityConnectionText, profileActivityInterestText;
    private Button settingsButton;
    private RelativeLayout connectionsTab;
    private RelativeLayout interestsTab;
    private RecyclerView connectionsRecyclerView;
    private RecyclerView interestsRecyclerView;
    private ProfileConnectionAdapter profileConnectionAdapter;
    private ProfileInterestAdapter interestAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.profile_view, container, false);

        FirebaseModel fbModel = FirebaseModel.getInstance(getContext());
        User user = fbModel.getCurrentUser();
        final ArrayList<User> users = user.getListOfFriends();

        final List<Interest> interests = user.getListOfInterests();

        connectionsRecyclerView = (RecyclerView) v.findViewById(R.id.profile_recyclerview);
        interestsRecyclerView = (RecyclerView) v.findViewById(R.id.profile_card_recyclerview);
        profileImage = (ImageView) v.findViewById(R.id.profile_photo);
        profileImage.setImageResource(user.getProfilePictureResource());
        profileName = (TextView) v.findViewById(R.id.profile_name);
        profileName.setText(user.getFirstName() + " " + user.getLastName());
        profilePosition = (TextView) v.findViewById(R.id.profile_field);
        profilePosition.setText("");
        quote = (TextView) v.findViewById(R.id.profile_quote);
        quote.setText("");
        settingsButton = (Button) v.findViewById(R.id.profile_settings_button);
        connectionsTab = (RelativeLayout) v.findViewById(R.id.profile_connections_button);
        profileActivityConnectionText = (TextView) v.findViewById(R.id.profile_activity_connection_text);
        profileActivityConnectionText.setText(String.valueOf(user.getNumberOfFriends()));
        interestsTab = (RelativeLayout) v.findViewById(R.id.profile_common_interest_button);
        profileActivityInterestText = (TextView) v.findViewById(R.id.profile_activity_interest_text);
        profileActivityInterestText.setText(String.valueOf(user.getNumberOfInterests()));

        profileConnectionAdapter = new ProfileConnectionAdapter(getContext(), users);
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
