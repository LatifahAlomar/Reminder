package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//source code from :
//https://www.allcodingtutorials.com/post/insert-delete-update-and-view-data-in-sqlite-database-android-studio

public class database extends SQLiteOpenHelper {
    public database( Context context ) {
        super(context, "Reminders", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table reminder(title TEXT primary key, date DATE, time TIME,importance TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists reminder");

    }

    public Boolean insertuserdata(String title, String date, String time , String importance)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("importance", importance);

        long result=DB.insert("reminder", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean deletedata (String title)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from reminder where title = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            long result = DB.delete("reminder", "title=?", new String[]{title});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from reminder", null);
        //Cursor cursor = DB.rawQuery("Select * from reminder Order By date ASC", null);//
        return cursor;

    }
}


