package com.anteriore.colab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anteriore.colab.Model.Notification;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private RecyclerView notificationsRecyclerView;
    private NotificationAdapter notificationsAdapter;
    private Paint p = new Paint();
    private FirebaseModel fbModel;
    private ArrayList<Notification> notifications;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.notifications_view, container, false);

        fbModel = FirebaseModel.getInstance(getContext());
        notifications = fbModel.getNotificationList();
        /*
        notifications.add(new Notification(R.drawable.profile_chino, "Chino Tapales requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_chris, "Chris Angping requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_david, "David Gamboa requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_chino, "Chino Tapales requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_chris, "Chris Angping requested to follow you"));
        notifications.add(new Notification(R.drawable.profile_david, "David Gamboa requested to follow you"));
        */

        notificationsRecyclerView = (RecyclerView) v.findViewById(R.id.notification_recyclerview);

        notificationsAdapter = new NotificationAdapter(getContext(), notifications);
        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        notificationsRecyclerView.setAdapter(notificationsAdapter);
        initSwipe();

        return v;
    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction){
                int position = viewHolder.getAdapterPosition();
                if(direction == ItemTouchHelper.LEFT){
                    fbModel.removeNotificationFromUser(notifications.get(position));
                } if (direction == ItemTouchHelper.RIGHT){
                    fbModel.addAsFriendsThroughNotification(notifications.get(position));
                    fbModel.removeNotificationFromUser(notifications.get(position));
                }
            }
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;
                    if(dX > 0){
                        p.setColor(Color.parseColor("#F5F5F5"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.like);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                    else{
                        p.setColor(Color.parseColor("#F5F5F5"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.dislike);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(notificationsRecyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        notificationsRecyclerView.setAdapter(notificationsAdapter);
        initSwipe();
    }
}
