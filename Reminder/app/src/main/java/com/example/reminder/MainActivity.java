package com.example.reminder;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Dialog newRminder ; //this is the dialog for adding new reminder , in this dialog there is 3 input text the first for the title second for date thierd one for the time
    Button doneButton;
    database DB = new database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Reminder");

        newRminder = new Dialog(this);
        newRminder.setContentView(R.layout.add_reminder_dialog);
        doneButton = newRminder.findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                if(checking){
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
}