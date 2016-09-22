package com.anteriore.colab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anteriore.colab.Model.Interest;

import java.util.List;

public class InterestProfileAdapter extends RecyclerView.Adapter<InterestProfileAdapter.InterestHolder> {

    private List<Interest> interestList;

    public InterestProfileAdapter(List<Interest> interestList) {
        this.interestList = interestList;
    }

    public class InterestHolder extends RecyclerView.ViewHolder {
        TextView interestTitle;
        String interestID;
        String interestType;
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
        String interestName = interestList.get(position).getInterestName();

        holder.interestTitle.setText(interestName);
        holder.interestID = interestList.get(position).getInterestID();
        holder.interestType = interestList.get(position).getInterestType().toString();
        holder.interestBackground.setImageResource(interestList.get(position).getInterestImageResource());
    }

    @Override
    public int getItemCount() {
        return this.interestList.size();
    }

}

