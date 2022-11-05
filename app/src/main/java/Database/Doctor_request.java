package Database;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.blood.user_self_add_doc;

public class Doctor_request extends SQLiteOpenHelper {
    public static final String TABLENAME = "doctor_request";
    public static final String DBNAME = "BlueBlood.db";


    public Doctor_request(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, 1); //choose 1st constructor and do this inside of it
    }

    public Doctor_request(user_self_add_doc admin_add_doc_brief, String TABLENAME, Context context, int i) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists doctor_request ");
    }

    public Boolean AddNewEntry(String Name, String Email, String contact, String Hospital) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", Name);
        values.put("Email", Email);
        values.put("Contact", contact);
        values.put("Hospital", Hospital);


        long result = MyDB.insert("doctor_request ", null, values);
        if (result == -1) return false;
        else
            return true;
    }

    public Cursor readData() {
        String querry = "SELECT * FROM " + TABLENAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querry, null);

        if (cursor.getCount() > 0)
            return cursor;
        else
            return null;
    }

    public Cursor readFromID(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + TABLENAME + " Where ID =?", new String[]{id});

        if (cursor.getCount() > 0) return cursor;
        else return null;
    }

    public Boolean approveDoctor(String Name, String Email, String contact, String Hospital) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", Name);
        values.put("Email", Email);
        values.put("Contact", contact);
        values.put("Hospital", Hospital);


        long result = MyDB.insert("doctor ", null, values);
        if (result == -1) return false;
        else return true;
    }
    public Cursor allDocList() {
        String querry = "SELECT * FROM doctor";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querry, null);

        if (cursor.getCount() > 0)
            return cursor;
        else
            return null;
    }


    public Boolean DeleteOneRow(String reqID) {
        SQLiteDatabase MyDB = getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where ID =?", new String[]{reqID});

        if (cursor.getCount() > 0) { //To check the number of records>0 in table
            long retVal = MyDB.delete(TABLENAME, "ID =?", new String[]{reqID});

            if (retVal != -1) return true;
            else return false;
        } else return false;
    }

    public Boolean Update(String Name, String Email, String contact, String Hospital) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map

        values.put("NAME", Name);
        values.put("Email", Email);
        values.put("Contact", contact);
        values.put("Hospital", Hospital);

        Cursor cursor = db.rawQuery("Select * from " + TABLENAME + " where ID =?", new String[]{ID});

        if (cursor.getCount() > 0) {
            long retVal = db.update(TABLENAME, values, "ID =?", new String[]{ID});

            if (retVal != -1)
                return true;
            else
                return false;
        } else
            return false;
    }
}



