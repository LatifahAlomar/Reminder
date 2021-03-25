package com.example.reminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reminder.Notification.reminderNotification;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Dialog newRminder ; //this is the dialog for adding new reminder , in this dialog there is 3 input text the first for the title second for date thierd one for the time
    Button doneButton;
    database DB = new database(this);
    ArrayList<remind_card> cardArrayList;
    Adapter listAdapter;
    RecyclerView recyclerView;
    static EditText dateInput, timeInput;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Reminder");
        newRminder = new Dialog(this);
        newRminder.setContentView(R.layout.add_reminder_dialog);
        doneButton = newRminder.findViewById(R.id.doneButton);
        dateInput = newRminder.findViewById(R.id.dateInput);
        timeInput = newRminder.findViewById(R.id.TimeInput);

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });
        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        cardArrayList = new ArrayList<>();
        listAdapter  = new Adapter(cardArrayList);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        updateList();
        reminderNotification.init(context);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //database insertion

                EditText titleInput = newRminder.findViewById(R.id.TitleEditText);
                EditText dateInput = newRminder.findViewById(R.id.dateInput);
                EditText timeInput = newRminder.findViewById(R.id.TimeInput);
                SwitchCompat priorityInput = newRminder.findViewById(R.id.PrioritySwitch);
                boolean checked = priorityInput.isChecked();

                String priority = checked? "High" : "Low";
                String title = titleInput.getText().toString();
                String date = dateInput.getText().toString();
                String time = timeInput.getText().toString();


                boolean empty = priority.isEmpty() || title.isEmpty() || date.isEmpty() || time.isEmpty();
                if (empty) {
                    newRminder.dismiss();
                    return;
                }
                Boolean checking = DB.insertuserdata(title,date,time,priority);


                if(checking){
                    reminderNotification.addReminder(context, title,date,time,priority);
                    newRminder.dismiss();
                    Context context = getApplicationContext();
                    Toast.makeText(context, "added a new reminder successfully", Toast.LENGTH_SHORT).show();
                    updateList();
                }
                else{
                    Context context = getApplicationContext();
                    Toast.makeText(context, "try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newRminder.show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }


    public void updateList(){
        ArrayList<remind_card> updatedCardArrayList = new ArrayList<>();
        Cursor cursor = DB.getdata();
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("title");
            String t = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("date");
            String d = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("time");
            String tim = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("importance");
            String p = cursor.getString(index);

            updatedCardArrayList.add(new remind_card(p,t,d,tim));
        }
        listAdapter.mExampleList = updatedCardArrayList;
        listAdapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String date = dateTimeParser(month+1) + "/" + dateTimeParser(day) + "/" + year;
            dateInput.setText(date);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String time = dateTimeParser(hourOfDay)+":"+dateTimeParser(minute);
            timeInput.setText(time);
        }
    }
    public static String dateTimeParser(int n){
        String number = n + "";
        switch (number.length()){
            case 1: return "0"+number;
            case 2: return number;
        }
        return "00";
    }
}