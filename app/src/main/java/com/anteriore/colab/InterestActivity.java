package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    private RecyclerView interestRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        interestRecyclerView = (RecyclerView) findViewById(R.id.interest_recyclerview);

        ArrayList<Interest> interestList = new ArrayList<>();

        interestList.add(new Interest("LIKES", R.drawable.interest_likes));
        interestList.add(new Interest("HOBBIES", R.drawable.interest_hobby));
        interestList.add(new Interest("PASSION", R.drawable.interest_passion));
        interestList.add(new Interest("IDEOLOGIES", R.drawable.interest_ideology));

        final InterestAdapter interestAdapter = new InterestAdapter(interestList);

        interestRecyclerView.setAdapter(interestAdapter);

        interestRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }
}
