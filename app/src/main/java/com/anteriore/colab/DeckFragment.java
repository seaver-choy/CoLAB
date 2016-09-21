package com.anteriore.colab;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anteriore.colab.Model.Interest;
import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public class DeckFragment extends Fragment {

    private SwipeDeck cardStack;
    private ArrayList<Interest> cardData;
    private SwipeDeckAdapter adapter;
    private Button dislikeButton;
    private Button likeButton;
    private FirebaseModel fbModel;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_card, container, false);

        fbModel = new FirebaseModel();
        dislikeButton = (Button) v.findViewById(R.id.swipe_left);
        likeButton = (Button) v.findViewById(R.id.swipe_right);
        cardStack = (SwipeDeck) v.findViewById(R.id.swipe_deck);

        dislikeButton.getBackground().setAlpha(192);
        likeButton.getBackground().setAlpha(192);

        cardData = new ArrayList();

        ChildEventListener CEL = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Child Added", dataSnapshot.getKey());
                if(dataSnapshot.child("interestName").exists()) {
                    Interest currLike = dataSnapshot.getValue(Interest.class);

                    Resources resources = getActivity().getApplicationContext().getResources();
                    final int resourceId = resources.getIdentifier(currLike.getInterestImage(), "drawable",
                            getActivity().getApplication().getApplicationContext().getPackageName());

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

        fbModel.getmDatabase().child("colab").child("interests").child("hobby").child("sports").addChildEventListener(CEL);
        fbModel.getmDatabase().removeEventListener(CEL);

        adapter = new SwipeDeckAdapter(cardData, this.getContext());
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
