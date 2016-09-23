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

        fbModel = FirebaseModel.getInstance(getContext());
        final ArrayList<Interest> likeList = fbModel.getLikeList();
        final ArrayList<Interest> passionList = fbModel.getPassionList();
        final ArrayList<Interest> hobbyList = fbModel.getHobbyList();
        likesRecyclerView = (RecyclerView) v.findViewById(R.id.likes_recyclerview);
        hobbiesRecyclerView = (RecyclerView) v.findViewById(R.id.hobbies_recyclerview);
        passionRecyclerView = (RecyclerView) v.findViewById(R.id.passion_recyclerview);

        skipStep = (TextView) v.findViewById(R.id.skip_step_text);
        skipStep.setVisibility(View.GONE);

        final InterestAdapter likeAdapter = new InterestAdapter(likeList);
        final InterestAdapter passionAdapter = new InterestAdapter(passionList);
        final InterestAdapter hobbyAdapter = new InterestAdapter(hobbyList);

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
