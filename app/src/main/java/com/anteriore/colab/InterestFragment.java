package com.anteriore.colab;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anteriore.colab.Model.Interest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class InterestFragment extends Fragment {

    private FirebaseModel fbModel;
    private RecyclerView likesRecyclerView;
    private RecyclerView hobbiesRecyclerView;
    private RecyclerView passionRecyclerView;
    private TextView skipStep;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_interest, container, false);

        fbModel = new FirebaseModel();
        final ArrayList<Interest> likeList = new ArrayList<>();
        final ArrayList<Interest> passionList = new ArrayList<>();
        final ArrayList<Interest> hobbyList = new ArrayList<>();
        likesRecyclerView = (RecyclerView) v.findViewById(R.id.likes_recyclerview);
        hobbiesRecyclerView = (RecyclerView) v.findViewById(R.id.hobbies_recyclerview);
        passionRecyclerView = (RecyclerView) v.findViewById(R.id.passion_recyclerview);

        skipStep = (TextView) v.findViewById(R.id.skip_step_text);
        skipStep.setVisibility(View.GONE);

        final InterestAdapter likeAdapter = new InterestAdapter(likeList);
        final InterestAdapter passionAdapter = new InterestAdapter(passionList);
        final InterestAdapter hobbyAdapter = new InterestAdapter(hobbyList);

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currLike = dataSnapshot.getValue(Interest.class);

                    Resources resources = getActivity().getApplicationContext().getResources();
                    final int resourceId = resources.getIdentifier(currLike.getInterestImage(), "drawable",
                            getActivity().getApplication().getApplicationContext().getPackageName());

                    currLike.setInterestType(Interest.interestTypes.like);
                    currLike.setInterestID(dataSnapshot.getKey());
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
                            getActivity().getPackageName());

                    currHobby.setInterestType(Interest.interestTypes.hobby);
                    currHobby.setInterestID(dataSnapshot.getKey());
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
                            getActivity().getPackageName());

                    currPassion.setInterestType(Interest.interestTypes.passion);
                    currPassion.setInterestID(dataSnapshot.getKey());
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

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
