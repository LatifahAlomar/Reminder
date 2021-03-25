package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReminderDetails extends AppCompatActivity {
    TextView titleView, priorityView, dateView, timeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);
        titleView = findViewById(R.id.Title);
        priorityView = findViewById(R.id.priority);
        dateView = findViewById(R.id.date);
        timeView = findViewById(R.id.Time);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String priority = intent.getStringExtra("priority");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        titleView.setText(title);
        priorityView.setText(priority);
        dateView.setText(date);
        timeView.setText(time);
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}