package com.anteriore.colab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anteriore.colab.Model.Interest;

import java.util.List;

public class ProfileInterestAdapter extends RecyclerView.Adapter<ProfileInterestAdapter.ProfileInterestHolder> {

    private List<Interest> interestList;

    public ProfileInterestAdapter(List<Interest> interestList) {
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
        String interestName = interestList.get(position).getInterestName();

        holder.interestTitle.setText(interestName);
        holder.interestBackground.setImageResource(interestList.get(position).getInterestImageResource());
    }

    @Override
    public int getItemCount() {
        return this.interestList.size();
    }

}

