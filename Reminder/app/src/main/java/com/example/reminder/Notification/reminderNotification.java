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

    public static void init(Context context){
        createNotificationChannel(context);
    }

    private static void createNotificationChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.reminder_notification);
            String description = context.getString(R.string.reminder_notification);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("reminder_notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public static void addReminder(Context context, String title, String priority, String date, String time){

        Intent intent = new Intent(context, reminderBroadcastReceiver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        //String Date = "03/25/2021 GMT+03:00 15:52";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy z HH:mm:ss", Locale.ENGLISH);
        LocalDateTime localDate = LocalDateTime.parse(date + " GMT+03:00 " + time, formatter);
        long timeInMilliseconds = localDate.atOffset(OffsetDateTime.now().getOffset()).toInstant().toEpochMilli();

        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT < 19) {
            alarm.set(AlarmManager.RTC_WAKEUP, timeInMilliseconds, pendingIntent);
        } else {
            alarm.setExact(AlarmManager.RTC_WAKEUP, timeInMilliseconds, pendingIntent);
        }

    }


}
