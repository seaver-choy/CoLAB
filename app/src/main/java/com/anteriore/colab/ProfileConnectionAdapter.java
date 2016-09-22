package com.anteriore.colab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anteriore.colab.Model.User;

import java.util.List;

public class ProfileConnectionAdapter extends RecyclerView.Adapter<ProfileConnectionAdapter.ProfileConnectionViewHolder> {

    private Context context;
    private List<User> users;

    public ProfileConnectionAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public static class ProfileConnectionViewHolder extends RecyclerView.ViewHolder {
        CardView connectionCard;
        TextView connectionName, connectionCount, commonInterestCount;
        ImageView connectionImage;
        User currentUser;

        public ProfileConnectionViewHolder(View itemView) {
            super(itemView);
            connectionCard = (CardView) itemView.findViewById(R.id.profile_item_card);
            connectionName = (TextView) itemView.findViewById(R.id.profile_item_name);
            connectionCount = (TextView) itemView.findViewById(R.id.profile_item_connections);
            commonInterestCount = (TextView) itemView.findViewById(R.id.profile_item_common_interests);
            connectionImage = (ImageView) itemView.findViewById(R.id.profile_item_photo);

            connectionCard.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra("selectedUser", currentUser);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public ProfileConnectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        ProfileConnectionViewHolder viewHolder = new ProfileConnectionViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ProfileConnectionViewHolder holder, int position) {
        holder.connectionImage.setImageResource(users.get(position).getProfilePictureResource());
        String username = users.get(position).getFirstName() + " " + users.get(position).getLastName();
        holder.connectionName.setText(username);
        String connectionCount = users.get(position).getNumberOfFriends() + " connections";
        holder.connectionCount.setText(connectionCount);
        String commonInterestCount = users.get(position).getNumberOfInterests() + " common interests";
        holder.commonInterestCount.setText(commonInterestCount);
        holder.currentUser = users.get(position);
    }

    public int getItemCount() {
        return users.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}