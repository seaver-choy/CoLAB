package com.anteriore.colab;


import android.app.SearchableInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private SearchView chatMenuSearch;
    private Button chatMenuNewButon;
    private RecyclerView chatMenuRecyclerView;
    private ChatMenuAdapter chatMenuAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.chat_view, container, false);

        final ArrayList<MessagePreview> messagePreviews = new ArrayList<>();

        messagePreviews.add(new MessagePreview("Chino Tapales", "Hey, What's up?", "10:00 PM", R.drawable.profile_chino));
        messagePreviews.add(new MessagePreview("Chris Angping", "The one who got lit fam.", "10:00 PM", R.drawable.profile_chris));
        messagePreviews.add(new MessagePreview("David Gamboa", "amputa", "10:00 PM", R.drawable.profile_david));
        messagePreviews.add(new MessagePreview("Seaver Choy", "chloe ng chloe", "10:00 PM", R.drawable.profile_seaver));

        chatMenuRecyclerView = (RecyclerView) v.findViewById(R.id.chat_menu_recyclerview);
        chatMenuSearch = (SearchView) v.findViewById(R.id.chat_menu_search);
        chatMenuSearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HEHE", "Search button clicked");
            }
        });
        chatMenuSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        chatMenuNewButon = (Button) v.findViewById(R.id.chat_menu_add_button);


        chatMenuAdapter = new ChatMenuAdapter(messagePreviews);
        chatMenuRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        chatMenuRecyclerView.setAdapter(chatMenuAdapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
