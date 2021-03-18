package com.example.reminder;
//source: https://www.youtube.com/watch?v=Nw9JF55LDzE
public class remind_card {
    private String Priority;
    private String Time;
    private String Date;
    private String Task;

    public remind_card (String priority,String task , String date, String time ){
        Priority=priority;
        Task=task;
        Time=time;
        Date=date;
    }
    public String getPriority(){
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