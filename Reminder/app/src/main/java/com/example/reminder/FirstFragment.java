package com.example.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    ArrayList<remind_card> cardArrayList;
    database DB;
        @Override
    public View onCreateView(
            LayoutInflater inflater, @NonNull ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.FrigmantrecyclerView);
        DB = new database(getContext());
         cardArrayList = new ArrayList<>();
//        Cursor cursor = DB.getdata();
//        while(cursor.moveToNext()) {
//            int index;
//            index = cursor.getColumnIndexOrThrow("title");
//            String t = cursor.getString(index);
//
//            index = cursor.getColumnIndexOrThrow("date");
//            String d = cursor.getString(index);
//
//            index = cursor.getColumnIndexOrThrow("time");
//            String tim = cursor.getString(index);
//
//            index = cursor.getColumnIndexOrThrow("importance");
//            String p = cursor.getString(index);
//            cardArrayList.add(new remind_card(p,t,d,tim));
//        }

        Adapter listAdapter = new Adapter(cardArrayList) ;
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//              // newRminder.show();
//            }
//        });
    }



}