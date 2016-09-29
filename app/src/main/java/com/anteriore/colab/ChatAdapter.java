package com.anteriore.colab;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<MessageItem> messages;

    public ChatAdapter(List<MessageItem> messages) {
        this.messages = messages;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parentContainer;
        CardView chatCard;
        TextView chatMessage;

        public ChatViewHolder(View itemView) {
            super(itemView);
            parentContainer = (RelativeLayout) itemView.findViewById(R.id.chat_container);
            chatCard = (CardView) itemView.findViewById(R.id.chat_card);
            chatMessage = (TextView) itemView.findViewById(R.id.chat_text);
        }
    }

    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        ChatViewHolder viewHolder = new ChatViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ChatViewHolder holder, int position) {
        if(position % 2 == 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            params.weight = 1.0f;
            params.gravity = Gravity.END;
            holder.parentContainer.setLayoutParams(params);
            holder.chatCard.setCardBackgroundColor(R.color.colorMessage);
            holder.chatMessage.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            params.weight = 1.0f;
            params.gravity = Gravity.START;
            holder.parentContainer.setLayoutParams(params);
            holder.chatMessage.setTextColor(Color.parseColor("#000000"));

        }
        String previousMessage = messages.get(position).getMessage();
        holder.chatMessage.setText(previousMessage);
    }

    public int getItemCount() {
        return messages.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}