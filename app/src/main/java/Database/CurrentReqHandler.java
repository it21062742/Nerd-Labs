package Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CurrentReqHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueBlood.db";
    ContentResolver mResolver;

    public CurrentReqHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, 1); //choose 1st constructor and do this inside of it
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + CurrentUser.PresentUser.TABLENAME +
                "(" + CurrentUser.PresentUser.id + " TEXT PRIMARY KEY, " +
                CurrentUser.PresentUser.EMAIL + " TEXT)";

        db.execSQL(CreateTable);
    }

    public Cursor getUser()
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from CurrentUser", null);
        return cursor;
    }

    public Boolean addUser(String email) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map
        values.put(CurrentUser.PresentUser.EMAIL, email);

        long retVal = db.insert(CurrentUser.PresentUser.TABLENAME, null, values);

        if (retVal != -1)
            return true;
        else
            return false;
    }

    public boolean updateUser(String email)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CurrentUser.PresentUser.EMAIL, email);

        long retVal = db.update(CurrentUser.PresentUser.TABLENAME, values, CurrentUser.PresentUser.id + " =?", new String[]{"1"});

        if(retVal==1) return true;
        else return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
