package com.example.reminder.Notification;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.reminder.R;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class reminderNotification {

    public static NotificationChannel highPriorityChannel;
    public static NotificationChannel lowPriorityChannel;

    public static void init(Context context){
        createNotificationChannel(context);
    }

    private static void createNotificationChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Names
            CharSequence highPriorityChannelName = context.getString(R.string.highPriorityChannel);
            CharSequence lowPriorityChannelName = context.getString(R.string.lowPriorityChannel);

            // Description
            String description = context.getString(R.string.reminder_notification);

            // Imprtance
            int highPriority = NotificationManager.IMPORTANCE_HIGH;
            int lowPriority = NotificationManager.IMPORTANCE_LOW;

            // High priority channel
            highPriorityChannel = new NotificationChannel("high_priority", highPriorityChannelName, highPriority);
            highPriorityChannel.setDescription(description);

            // Low priority channel
            lowPriorityChannel = new NotificationChannel("low_priority", lowPriorityChannelName, lowPriority);
            lowPriorityChannel.setDescription(description);

            // Create Notification Manager
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

            // Create Channels
            notificationManager.createNotificationChannel(highPriorityChannel);
            notificationManager.createNotificationChannel(lowPriorityChannel);
        }
    }

    public NotificationChannel getHighPriorityChannel(){
        return highPriorityChannel;
    }

    public NotificationChannel getLowPriorityChannel(){
        return lowPriorityChannel;
    }


    public static void addReminder(Context context, String title, String date, String time, String priority){

        Intent intent = new Intent(context, reminderBroadcastReceiver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("title", title);
        intent.putExtra("priority", priority);
        intent.putExtra("date", date);
        intent.putExtra("time", time);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);



        String x = "03/25/2021 GMT+03:00 19:22";
        String d = date + " GMT+03:00 " + time;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy z HH:mm", Locale.ENGLISH);

        LocalDateTime localDate = LocalDateTime.parse(d, formatter);
        long timeInMilliseconds = localDate.atOffset(OffsetDateTime.now().getOffset()).toInstant().toEpochMilli();

        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT < 19) {
            alarm.set(AlarmManager.RTC_WAKEUP, timeInMilliseconds, pendingIntent);
        } else {
            alarm.setExact(AlarmManager.RTC_WAKEUP, timeInMilliseconds, pendingIntent);
        }

    }


}
