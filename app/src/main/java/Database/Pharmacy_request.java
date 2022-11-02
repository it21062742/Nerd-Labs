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

    public Pharmacy_request(@Nullable Context context, @Nullable String name, int version) {
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

        long result = MyDB.insert("pharmacy_request ", null, values);
        if(result==-1) return false;
        else
            return true;
    }
    Cursor readData(){
        String querry = "SELECT * FROM pharmacy_request";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(querry,null);
        }
        return cursor;
    }
}

