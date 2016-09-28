package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private Button backButton;
    private Button sendButton;
    private EditText textBox;
    private TextView chatTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ArrayList<MessageItem> messages = new ArrayList<>();

        messages.add(new MessageItem(1, "Hey, What's up?", "Chino Tapales"));
        messages.add(new MessageItem(2, "The one who got lit fam.", "Chris Angping"));
        messages.add(new MessageItem(3, "Lol", "Chino Tapales"));
        messages.add(new MessageItem(4, "Shmehhh", "Chris Angping"));

        messages.add(new MessageItem(1, "Hey, What's up?", "Chino Tapales"));
        messages.add(new MessageItem(2, "The one who got lit fam.", "Chris Angping"));
        messages.add(new MessageItem(3, "Lol", "Chino Tapales"));
        messages.add(new MessageItem(4, "Shmehhh", "Chris Angping"));

        messages.add(new MessageItem(1, "Hey, What's up?", "Chino Tapales"));
        messages.add(new MessageItem(2, "The one who got lit fam.", "Chris Angping"));
        messages.add(new MessageItem(3, "Lol", "Chino Tapales"));
        messages.add(new MessageItem(4, "Shmehhh", "Chris Angping"));

        backButton = (Button) findViewById(R.id.chat_back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        chatRecyclerView = (RecyclerView) findViewById(R.id.chat_recyclerview);

        textBox = (EditText) findViewById(R.id.chat_edit_text);

        sendButton = (Button) findViewById(R.id.chat_send_button);

        chatTitle = (TextView) findViewById(R.id.chat_person);

        chatAdapter = new ChatAdapter(messages);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

    }
}
