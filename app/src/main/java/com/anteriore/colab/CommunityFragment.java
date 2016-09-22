package com.anteriore.colab;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anteriore.colab.Model.Interest;
import com.anteriore.colab.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommunityFragment extends Fragment {

    private RecyclerView connectionsRecyclerView;
    private ProfileConnectionAdapter profileConnectionAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.community_view, container, false);

        FirebaseModel fbModel = new FirebaseModel();
        /*
        final ArrayList<ProfileConnection> connections = new ArrayList<>();

        connections.add(new ProfileConnection("Chino Tapales", "10 connections", "26 common interests", R.drawable.profile_chino));
        connections.add(new ProfileConnection("Chris Angping", "12 connections", "31 common interestes", R.drawable.profile_chris));
        connections.add(new ProfileConnection("David Gamboa", "14 connections", "52 common interestes", R.drawable.profile_david));
        connections.add(new ProfileConnection("Seaver Choy", "16 connections", "12 common interests", R.drawable.profile_seaver));
        */

        final ArrayList<User> currentUsers = new ArrayList();

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());

                User currUser = dataSnapshot.getValue(User.class);

                GenericTypeIndicator<HashMap<String, Interest>> t = new GenericTypeIndicator<HashMap<String,Interest>>(){};
                HashMap<String, Interest> tempList = dataSnapshot.child(User.FirebaseInterestList).getValue(t);


                ArrayList<Interest> currInterests = new ArrayList();
                for(Interest value: tempList.values())
                {
                    currInterests.add(value);
                }

                currUser.setCurrInterests(currInterests);
                Resources resources = getResources();
                final int resourceId = resources.getIdentifier("profile", "drawable",
                            getActivity().getPackageName());

                currUser.setProfilePictureResource(resourceId);

                currentUsers.add(currUser);

                profileConnectionAdapter.notifyDataSetChanged();
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

        fbModel.getmDatabase().child("colab").child(User.FirebaseChildName).addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        connectionsRecyclerView = (RecyclerView) v.findViewById(R.id.community_recyclerview);

        profileConnectionAdapter = new ProfileConnectionAdapter(getContext(), currentUsers);
        connectionsRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        connectionsRecyclerView.setAdapter(profileConnectionAdapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
