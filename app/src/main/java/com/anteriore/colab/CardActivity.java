package com.anteriore.colab;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anteriore.colab.Model.Interest;
import com.anteriore.colab.Model.User;
import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    private SwipeDeck cardStack;
    private ArrayList<Interest> cardData;
    private SwipeDeckAdapter adapter;
    private Button dislikeButton;
    private Button likeButton;
    private Button backButton;
    private TextView titleText;
    private FirebaseAuth auth;
    private FirebaseModel fbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        auth = FirebaseAuth.getInstance();

        fbModel = new FirebaseModel();

        titleText = (TextView) findViewById(R.id.card_title);

        backButton = (Button) findViewById(R.id.card_back_button);
        dislikeButton = (Button) findViewById(R.id.swipe_left);
        likeButton = (Button) findViewById(R.id.swipe_right);
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        dislikeButton.getBackground().setAlpha(192);
        likeButton.getBackground().setAlpha(192);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String interestID = getIntent().getStringExtra(Interest.interestNameConstant);
        String interestType = getIntent().getStringExtra(Interest.interestTypeConstant);

        titleText.setText(interestID);

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

        fbModel.getmDatabase().child("colab").child(Interest.FirebaseChildName).child(interestType).child(interestID).addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        adapter = new SwipeDeckAdapter(cardData, this);
        if(cardStack != null){
            cardStack.setAdapter(adapter);
        }

        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long positionInAdapter) {
                Log.i("CardActivity", "card was swiped left, position in adapter: " + positionInAdapter);
                if (cardData.size() - 1 == (int) positionInAdapter) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void cardSwipedRight(long positionInAdapter) {
                Log.i("CardActivity", "card was swiped right, position in adapter: " + positionInAdapter);

                fbModel.getUserFromDatabase(auth.getCurrentUser().getUid()).child(User.FirebaseInterestList)
                        .push().setValue(cardData.get((int) positionInAdapter));
                if (cardData.size() - 1== (int) positionInAdapter) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
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
