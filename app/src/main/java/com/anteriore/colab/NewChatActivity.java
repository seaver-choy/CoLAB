package com.anteriore.colab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class NewChatActivity extends AppCompatActivity {

    private SearchView chatNewMenuSearch;
    private RecyclerView chatNewMenuRecyclerView;
    private ChatMenuAdapter chatNewMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);

        final ArrayList<MessagePreview> messagePreviews = new ArrayList<>();

        messagePreviews.add(new MessagePreview("Chino Tapales", "Hey, What's up?", "10:00 PM", R.drawable.profile_chino));
        messagePreviews.add(new MessagePreview("Chris Angping", "The one who got lit fam.", "10:00 PM", R.drawable.profile_chris));
        messagePreviews.add(new MessagePreview("David Gamboa", "amputa", "10:00 PM", R.drawable.profile_david));
        messagePreviews.add(new MessagePreview("Seaver Choy", "chloe ng chloe", "10:00 PM", R.drawable.profile_seaver));

        chatNewMenuRecyclerView = (RecyclerView) findViewById(R.id.chat_new_menu_recyclerview);
        chatNewMenuSearch = (SearchView) findViewById(R.id.chat_new_menu_search);
        chatNewMenuSearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HEHE", "Search button clicked");
            }
        });
        chatNewMenuSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("HEHE", "submit button clicked");
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("HEHE", "i typed");
                return false;
            }
        });
        chatNewMenuAdapter = new ChatMenuAdapter(messagePreviews);
        chatNewMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatNewMenuRecyclerView.setAdapter(chatNewMenuAdapter);
    }

    @Override
    public void onResume() {
                super.onResume();
            }

}
