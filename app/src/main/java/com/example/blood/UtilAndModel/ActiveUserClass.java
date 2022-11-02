package com.example.blood.UtilAndModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.InfoBeforeImage;
import Database.InfoBeforeImageHandler;

public class ActiveUserClass {
    private CurrentReqHandler ch;
    private String email;
    private Context context;

    public ActiveUserClass(Context context) {
        this.context = context;
        ch = new CurrentReqHandler(context, CurrentUser.PresentUser.TABLENAME, null, 1);
    }

    public Cursor getUser()
    {
        return ch.getUser();
    }

    public String getEmail()
    {
        Cursor cursor = ch.getUser();
        cursor.moveToNext();
        email = String.valueOf(cursor.getString(1)).trim();

        return email;
    }

    public void addUser(String email) {
        ch.addUser(email);
    }

    public void updateUser(String email) {
        ch.updateUser(email);
    }

    public void operation(String email) {
        ActiveUserClass activeUser = new ActiveUserClass(context);

        Cursor cursor = activeUser.getUser();

        if (cursor.getCount() > 0 && email != null)
            activeUser.updateUser(email);
        else
            activeUser.addUser(email);
    }
}
