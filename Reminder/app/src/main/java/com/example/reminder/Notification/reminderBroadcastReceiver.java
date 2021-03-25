package com.example.reminder.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.reminder.R;

public class reminderBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        String title = intent.getStringExtra("title");
        String priority = intent.getStringExtra("priority");
        String channelId = "";
        int p = 0;
        switch (priority){
            case "High":{
                 p = NotificationCompat.PRIORITY_HIGH;
                 channelId = "high_priority";
            }
            break;
            case "Low":{
                p = NotificationCompat.PRIORITY_LOW;
                channelId = "low_priority";
            }
            break;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.redbutton)
                .setContentTitle("Reminder")
                .setContentText(title)
                .setPriority(p)
                // Set the intent that will fire when the user taps the notification
//                .setContentIntent(intent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }
}

