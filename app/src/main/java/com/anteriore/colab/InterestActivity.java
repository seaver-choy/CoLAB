package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    private RecyclerView likesRecyclerView;
    private RecyclerView hobbiesRecyclerView;
    private RecyclerView passionRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        likesRecyclerView = (RecyclerView) findViewById(R.id.likes_recyclerview);
        hobbiesRecyclerView = (RecyclerView) findViewById(R.id.hobbies_recyclerview);
        passionRecyclerView = (RecyclerView) findViewById(R.id.passion_recyclerview);

        ArrayList<Interest> interestList = new ArrayList<>();

        interestList.add(new Interest("LIKES", R.drawable.interest_likes));
        interestList.add(new Interest("HOBBIES", R.drawable.interest_hobby));
        interestList.add(new Interest("PASSION", R.drawable.interest_passion));
        interestList.add(new Interest("IDEOLOGIES", R.drawable.interest_ideology));

        final InterestAdapter interestAdapter = new InterestAdapter(interestList);

        likesRecyclerView.setAdapter(interestAdapter);
        hobbiesRecyclerView.setAdapter(interestAdapter);
        passionRecyclerView.setAdapter(interestAdapter);

        likesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        hobbiesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        passionRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

    }
}
