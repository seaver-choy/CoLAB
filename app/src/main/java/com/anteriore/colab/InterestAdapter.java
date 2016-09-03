package com.anteriore.colab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestHolder> {

    private List<Interest> interestList;

    public InterestAdapter(List<Interest> interestList) {
        this.interestList = interestList;
    }

    public class InterestHolder extends RecyclerView.ViewHolder {
        TextView interestTitle;
        ImageView interestBackground;
        public InterestHolder(View itemView) {
            super(itemView);
            interestTitle = (TextView) itemView.findViewById(R.id.interest_text);
            interestBackground = (ImageView) itemView.findViewById(R.id.interest_background);
        }
    }

    @Override
    public InterestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_item, null);
        return new InterestHolder(v);
    }

    @Override
    public void onBindViewHolder(InterestHolder holder, int position) {
        holder.interestTitle.setText(interestList.get(position).getInterestName());
        holder.interestBackground.setImageResource(interestList.get(position).getInterestImage());
    }

    @Override
    public int getItemCount() {
        return this.interestList.size();
    }

}

