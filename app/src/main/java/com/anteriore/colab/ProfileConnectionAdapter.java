package com.anteriore.colab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProfileConnectionAdapter extends RecyclerView.Adapter<ProfileConnectionAdapter.ProfileConnectionViewHolder> {

    private Context context;
    private List<ProfileConnection> connections;

    public ProfileConnectionAdapter(Context context, List<ProfileConnection> connections) {
        this.context = context;
        this.connections = connections;
    }

    public static class ProfileConnectionViewHolder extends RecyclerView.ViewHolder {
        TextView connectionName, connectionCount, commonInterestCount;
        ImageView connectionImage;

        public ProfileConnectionViewHolder(View itemView) {
            super(itemView);
            connectionName = (TextView) itemView.findViewById(R.id.profile_item_name);
            connectionCount = (TextView) itemView.findViewById(R.id.profile_item_connections);
            commonInterestCount = (TextView) itemView.findViewById(R.id.profile_item_common_interests);
            connectionImage = (ImageView
                    ) itemView.findViewById(R.id.profile_item_photo);
        }
    }

    public ProfileConnectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        ProfileConnectionViewHolder viewHolder = new ProfileConnectionViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ProfileConnectionViewHolder holder, int position) {
        holder.connectionImage.setImageResource(connections.get(position).getConnectionImageURL());
        holder.connectionName.setText(connections.get(position).getConnectionName());
        holder.connectionCount.setText(connections.get(position).getConnectionCount());
        holder.commonInterestCount.setText(connections.get(position).getCommonInterestCount());
    }

    public int getItemCount() {
        return connections.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}