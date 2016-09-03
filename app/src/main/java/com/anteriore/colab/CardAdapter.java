package com.anteriore.colab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<Card> cardList;

    public CardAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    public class CardHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        ImageView cardBackground;
        public CardHolder(View itemView) {
            super(itemView);
            cardTitle = (TextView) itemView.findViewById(R.id.card_text);
            cardBackground = (ImageView) itemView.findViewById(R.id.card_background);
        }
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, null);
        return new CardHolder(v);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.cardTitle.setText(cardList.get(position).getCardName());
        holder.cardBackground.setImageResource(cardList.get(position).getCardImage());
    }

    @Override
    public int getItemCount() {
        return this.cardList.size();
    }

}

