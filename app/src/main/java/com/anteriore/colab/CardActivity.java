package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.daprlabs.aaron.swipedeck.SwipeDeck;
import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    private SwipeDeck cardStack;
    private List testData;
    private SwipeDeckAdapter adapter;
    private Button dislikeButton;
    private Button likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        dislikeButton = (Button) findViewById(R.id.swipe_left);
        likeButton = (Button) findViewById(R.id.swipe_right);
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        dislikeButton.getBackground().setAlpha(192);
        likeButton.getBackground().setAlpha(192);

        testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");

        adapter = new SwipeDeckAdapter(testData, this);
        if(cardStack != null){
            cardStack.setAdapter(adapter);
        }
        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long positionInAdapter) {
                Log.i("CardActivity", "card was swiped left, position in adapter: " + positionInAdapter);
            }

            @Override
            public void cardSwipedRight(long positoinInAdapter) {
                Log.i("CardActivity", "card was swiped right, position in adapter: " + positoinInAdapter);

            }
        });
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardLeft(120);
            }
        });
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardRight(120);
            }
        });
    }
}
