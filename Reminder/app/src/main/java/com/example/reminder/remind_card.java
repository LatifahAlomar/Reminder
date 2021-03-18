package com.example.reminder;

public class remind_card {
    private int Priority;
    private String Time;
    private String Date;
    private String Task;

    public remind_card (int priority,String task , String date, String time ){
        Priority=priority;
        Task=task;
        Time=time;
        Date=date;
    }
    public int getPriority(){
        return Priority;
    }

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public String getTask() {
        return Task;
    }
}