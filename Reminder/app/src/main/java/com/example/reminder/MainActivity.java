package com.example.reminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.app.TimePickerDialog;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Dialog newRminder ; //this is the dialog for adding new reminder , in this dialog there is 3 input text the first for the title second for date thierd one for the time
    Button doneButton;
    EditText reminder_title, reminder_date, reminder_time;

    database DB = new database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Reminder");
        newRminder = new Dialog(this);

        newRminder = new Dialog(this);
        newRminder.setContentView(R.layout.add_reminder_dialog);
        reminder_title = newRminder.findViewById(R.id.title_input);
        pickersListeners();
        doneButton = newRminder.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newRminder.dismiss();
                //database insertion
                EditText priorityInput =newRminder.findViewById(R.id.Priority);
                EditText titleInput = newRminder.findViewById(R.id.TitleEditText);
                EditText dateInput = newRminder.findViewById(R.id.dateInput);
                EditText timeInput = newRminder.findViewById(R.id.TimeInput);

                String priority = priorityInput.getText().toString();
                String title = titleInput.getText().toString();
                String date = dateInput.getText().toString();
                String time = timeInput.getText().toString();

                Boolean checking = DB.insertuserdata(title,date,time,priority);
                ArrayList<remind_card> cardArrayList = new ArrayList<>();
                cardArrayList.add(new remind_card(priority,title,date,time));
                Adapter listAdapter = new Adapter(cardArrayList);
                if(checking){
                    listAdapter.notifyDataSetChanged();
                    newRminder.dismiss();
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "added a new reminder successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "try again", Toast.LENGTH_SHORT);
                    toast.show();
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

    public void pickersListeners(){
        reminder_date = newRminder.findViewById(R.id.date_input);
        reminder_time = newRminder.findViewById(R.id.time_input);

        reminder_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDateDialog(reminder_date);
            }

            private void showDateDialog(final EditText reminder_date) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
                        reminder_date.setText(simpleDateFormat.format(calendar));
                    }
                };
                new DatePickerDialog(MainActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        // time picker
        final EditText finalReminder_time = reminder_time;
        reminder_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showTimeDialog(finalReminder_time);
            }

            private void showTimeDialog(final EditText reminder_time) {
                final Calendar calendar = Calendar.getInstance();
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.UK);
                        reminder_time.setText(simpleDateFormat.format(calendar));
                    }
                };
                new TimePickerDialog(MainActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), false).show();
            }
        });

    }
}