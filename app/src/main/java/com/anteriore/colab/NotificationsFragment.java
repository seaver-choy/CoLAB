package com.anteriore.colab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private RecyclerView notificationsRecyclerView;
    private NotificationAdapter notificationsAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.notifications_view, container, false);

        final ArrayList<Notification> notifications = new ArrayList<>();

        notifications.add(new Notification(R.drawable.profile_chino, "Chino Tapales requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_chris, "Chris Angping requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_david, "David Gamboa requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_chino, "Chino Tapales requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_chris, "Chris Angping requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_david, "David Gamboa requested to follow you"));

        notificationsRecyclerView = (RecyclerView) v.findViewById(R.id.notification_recyclerview);

        notificationsAdapter = new NotificationAdapter(getContext(), notifications);
        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        notificationsRecyclerView.setAdapter(notificationsAdapter);

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
