package com.anteriore.colab;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anteriore.colab.Model.Interest;
import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    private SwipeDeck cardStack;
    private ArrayList<Interest> cardData;
    private SwipeDeckAdapter adapter;
    private Button dislikeButton;
    private Button likeButton;
    private FirebaseModel fbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        fbModel = new FirebaseModel();
        dislikeButton = (Button) findViewById(R.id.swipe_left);
        likeButton = (Button) findViewById(R.id.swipe_right);
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        dislikeButton.getBackground().setAlpha(192);
        likeButton.getBackground().setAlpha(192);

        cardData = new ArrayList();

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currLike = dataSnapshot.getValue(Interest.class);

                    Resources resources = getApplication().getApplicationContext().getResources();
                    final int resourceId = resources.getIdentifier(currLike.getInterestImage(), "drawable",
                            getApplication().getApplicationContext().getPackageName());

                    currLike.setInterestImageResource(resourceId);
                    cardData.add(currLike);
                }
                adapter.notifyDataSetChanged();
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

        fbModel.getmDatabase().child("colab").child("interests").child("like").child("artists").addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        adapter = new SwipeDeckAdapter(cardData, this);
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
