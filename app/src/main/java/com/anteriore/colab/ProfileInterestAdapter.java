package com.anteriore.colab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProfileInterestAdapter extends RecyclerView.Adapter<ProfileInterestAdapter.ProfileInterestHolder> {

    private List<ProfileInterest> interestList;

    public ProfileInterestAdapter(List<ProfileInterest> interestList) {
        this.interestList = interestList;
    }

    public class ProfileInterestHolder extends RecyclerView.ViewHolder {
        TextView interestTitle;
        ImageView interestBackground;
        public ProfileInterestHolder(View itemView) {
            super(itemView);
            interestTitle = (TextView) itemView.findViewById(R.id.interest_text);
            interestBackground = (ImageView) itemView.findViewById(R.id.interest_background);
        }
    }

    @Override
    public ProfileInterestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_item, null);
        return new ProfileInterestHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfileInterestHolder holder, int position) {
        String interestName = interestList.get(position).getInterestTitle();

        holder.interestTitle.setText(interestName);
        holder.interestBackground.setImageResource(interestList.get(position).getInterestImg());
    }

    @Override
    public int getItemCount() {
        return this.interestList.size();
    }

}

