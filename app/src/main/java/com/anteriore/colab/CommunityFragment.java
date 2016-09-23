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
import com.google.firebase.auth.FirebaseAuth;
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

        FirebaseModel fbModel = FirebaseModel.getInstance(getContext());
        /*
        final ArrayList<ProfileConnection> connections = new ArrayList<>();

        connections.add(new ProfileConnection("Chino Tapales", "10 connections", "26 common interests", R.drawable.profile_chino));
        connections.add(new ProfileConnection("Chris Angping", "12 connections", "31 common interestes", R.drawable.profile_chris));
        connections.add(new ProfileConnection("David Gamboa", "14 connections", "52 common interestes", R.drawable.profile_david));
        connections.add(new ProfileConnection("Seaver Choy", "16 connections", "12 common interests", R.drawable.profile_seaver));
        */

        final ArrayList<User> currentUsers = fbModel.getUserList();

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
