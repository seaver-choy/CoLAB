package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    private RecyclerView cardRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        cardRecyclerView = (RecyclerView) findViewById(R.id.card_recyclerview);

        ArrayList<Card> cardList = new ArrayList<>();

        cardList.add(new Card("LIKES", R.drawable.card_placeholder));
        cardList.add(new Card("HOBBIES", R.drawable.card_hobby));
        cardList.add(new Card("PASSION", R.drawable.card_passion));
        cardList.add(new Card("IDEOLOGIES", R.drawable.card_ideology));

        final CardAdapter cardAdapter = new CardAdapter(cardList);

        cardRecyclerView.setAdapter(cardAdapter);

        cardRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }
}
