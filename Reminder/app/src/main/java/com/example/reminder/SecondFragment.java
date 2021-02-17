package com.example.reminder;

import android.app.AlertDialog;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     //   https://stackoverflow.com/questions/2055509/how-to-create-a-date-and-time-picker-in-android
//
//        final View dialogView = View.inflate(MainActivity.class, R.layout.date_time_picker, null);
//        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
//
//        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//
//                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
//                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);
//
//                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
//                        datePicker.getMonth(),
//                        datePicker.getDayOfMonth(),
//                        timePicker.getCurrentHour(),
//                        timePicker.getCurrentMinute());
//
//                time = calendar.getTimeInMillis();
//                alertDialog.dismiss();
//            }});
//        alertDialog.setView(dialogView);
//        alertDialog.show();
    }
}