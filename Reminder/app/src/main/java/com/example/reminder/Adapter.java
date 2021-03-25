
package com.example.reminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {
    public ArrayList<remind_card> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView priority;
        public TextView task;
        public TextView date;
        public TextView time;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            priority = itemView.findViewById(R.id.priority);
            task = itemView.findViewById(R.id.Title);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.Time);
        }
    }
    public Adapter(ArrayList<remind_card> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.remider_card, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        remind_card currentReminder = mExampleList.get(position);
        holder.priority.setText(currentReminder.getPriority());
        holder.task.setText(currentReminder.getTask());
        holder.date.setText(currentReminder.getDate());
        holder.time.setText(currentReminder.getTime());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
