package com.anteriore.colab;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChatMenuAdapter extends RecyclerView.Adapter<ChatMenuAdapter.ChatMenuViewHolder> {

    private List<MessagePreview> messagePreviews;

    public ChatMenuAdapter(List<MessagePreview> messagePreviews) {
        this.messagePreviews = messagePreviews;
    }

    public static class ChatMenuViewHolder extends RecyclerView.ViewHolder {
        CardView chatCard;
        TextView chatName, chatMessage, chatTimestamp;
        ImageView chatImage;

        public ChatMenuViewHolder(View itemView) {
            super(itemView);
            chatCard = (CardView) itemView.findViewById(R.id.chat_menu_item_card);
            chatName = (TextView) itemView.findViewById(R.id.chat_menu_item_name);
            chatMessage = (TextView) itemView.findViewById(R.id.chat_menu_item_message);
            chatTimestamp = (TextView) itemView.findViewById(R.id.chat_menu_item_timestamp);
            chatImage = (ImageView) itemView.findViewById(R.id.chat_menu_item_photo);

            chatCard.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ChatActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public ChatMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_menu_item, parent, false);
        ChatMenuViewHolder viewHolder = new ChatMenuViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ChatMenuViewHolder holder, int position) {
        holder.chatImage.setImageResource(messagePreviews.get(position).getImageURL());
        String username = messagePreviews.get(position).getMessagePerson();
        holder.chatName.setText(username);
        String previousMessage = messagePreviews.get(position).getLastMessage();
        holder.chatMessage.setText(previousMessage);
        String timeStamp = messagePreviews.get(position).getTimeStamp();
        holder.chatTimestamp.setText(timeStamp);
    }

    public int getItemCount() {
        return messagePreviews.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}