package com.anteriore.colab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daprlabs.aaron.swipedeck.SwipeDeck;

import java.util.ArrayList;
import java.util.List;

public class DeckFragment extends Fragment {

    private SwipeDeck cardStack;
    private List testData;
    private SwipeDeckAdapter adapter;
    private Button dislikeButton;
    private Button likeButton;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_card, container, false);

        dislikeButton = (Button) v.findViewById(R.id.swipe_left);
        likeButton = (Button) v.findViewById(R.id.swipe_right);
        cardStack = (SwipeDeck) v.findViewById(R.id.swipe_deck);

        dislikeButton.getBackground().setAlpha(192);
        likeButton.getBackground().setAlpha(192);

        testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");

        adapter = new SwipeDeckAdapter(testData, getContext());
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

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
