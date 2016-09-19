package com.anteriore.colab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Context context;
    private List<Notification> notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView notificationText;
        ImageView notificationImage;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            notificationText = (TextView) itemView.findViewById(R.id.notification_item_text);
            notificationImage = (ImageView) itemView.findViewById(R.id.notification_item_photo);
        }
    }

    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        NotificationViewHolder viewHolder = new NotificationViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.notificationImage.setImageResource(notifications.get(position).getNotificationImg());
        holder.notificationText.setText(notifications.get(position).getNotificationText());
    }

    public int getItemCount() {
        return notifications.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}