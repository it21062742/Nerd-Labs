package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Pharmacy_request extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueBlood.db";
    public static final String TABLENAME ="pharmacy_request";

    public Pharmacy_request(@Nullable Context context, @Nullable String name, Object o, int version) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists pharmacy_request ");
    }

    public Boolean AddNewEntry(String Name, String Email, String contact, String address, String date) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("PharmacyName", Name);
        values.put("Email", Email);
        values.put("PharContact", contact);
        values.put("PharAddress", address);
        values.put("date", date);

        long result = MyDB.insert(TABLENAME, null, values);
        if(result==-1) return false;
        else
            return true;
    }
    public Cursor readDataPhar(){
        String querry = "SELECT * FROM "+TABLENAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(querry,null);
        }
        return cursor;
    }

    public Boolean approvePhar(String Name, String Email, String contact, String address) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("PharmacyName", Name);
        values.put("Email", Email);
        values.put("PharContact", contact);
        values.put("PharAddress", address);

        long result = MyDB.insert("pharmacy ", null, values);
        if (result == -1) return false;
        else return true;
    }
    public Cursor allPharList() {
        String querry = "SELECT * FROM pharmacy";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querry, null);

        if (cursor.getCount() > 0)
            return cursor;
        else
            return null;
    }
    public Boolean DeleteOneRow(String reqID) {
        SQLiteDatabase MyDB = getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where PharmacyID =?", new String[]{reqID});

        if (cursor.getCount() > 0) { //To check the number of records>0 in table
            long retVal = MyDB.delete(TABLENAME, "PharmacyID =?", new String[]{reqID});

            if (retVal != -1) return true;
            else return false;
        } else return false;
    }


    public Cursor readFromID(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + TABLENAME + " Where PharmacyID =?", new String[]{id});

        if (cursor.getCount() > 0) return cursor;
        else return null;
    }
}

