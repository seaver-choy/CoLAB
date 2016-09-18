package com.anteriore.colab;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> ahBottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        ahBottomNavigationItems.add(new AHBottomNavigationItem("Deck", R.drawable.cards));
        ahBottomNavigationItems.add(new AHBottomNavigationItem("Community", R.drawable.community));
        ahBottomNavigationItems.add(new AHBottomNavigationItem("coLAB", R.drawable.chat));
        ahBottomNavigationItems.add(new AHBottomNavigationItem("Notifications", R.drawable.notifications));
        ahBottomNavigationItems.add(new AHBottomNavigationItem("Profile", R.drawable.profile));

        bottomNavigation.addItems(ahBottomNavigationItems);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setAccentColor(Color.parseColor("#F06D72"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setCurrentItem(0);

        final ProfileFragment profile = new ProfileFragment();
        final CommunityFragment community = new CommunityFragment();
        final ChatFragment chat = new ChatFragment();
        final DeckFragment deck = new DeckFragment();
        final NotificationsFragment notifications = new NotificationsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.current_frame, deck, deck.getClass().getName())
                .commit();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener(){
            @Override
            public boolean onTabSelected(int position, boolean wasSelected){

                if(position == 0){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.current_frame, deck, deck.getClass().getName())
                            .commit();
                    bottomNavigation.setAccentColor(Color.parseColor("#F06D72"));
                }
                else if(position == 1){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.current_frame, community, community.getClass().getName())
                            .commit();
                    bottomNavigation.setAccentColor(Color.parseColor("#D2DD3C"));
                }
                else if(position == 2){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.current_frame, chat, chat.getClass().getName())
                            .commit();
                }
                else if(position == 3){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.current_frame, notifications, notifications.getClass().getName())
                            .commit();
                    bottomNavigation.setAccentColor(Color.parseColor("#F0B439"));
                }
                else if(position == 4){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.current_frame, profile, profile.getClass().getName())
                            .commit();
                    bottomNavigation.setAccentColor(Color.parseColor("#6CC1DF"));
                }

                bottomNavigation.setCurrentItem(position, wasSelected);
                bottomNavigation.refresh();

                return wasSelected;
            }
        });
    }
    //TODO: onResume()
}
