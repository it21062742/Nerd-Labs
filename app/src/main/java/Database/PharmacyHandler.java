package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PharmacyHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueBlood.db";

    public PharmacyHandler(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE pharmacy_request ("
                + "PharmacyID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "PharmacyName TEXT,"
                + "Email TEXT,"
                + "PharContact TEXT,"
                + "PharAddress TEXT,"
                + "PharDocuments TEXT,"
                + "date TEXT)";
        // method to execute above sql query
        db.execSQL(query);
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
        if (result == -1)
            return false;
        else
            return true;
    }

    Cursor readData() {
        String querry = "SELECT * FROM pharmacy_request";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(querry, null);
        }
        return cursor;
    }
}

