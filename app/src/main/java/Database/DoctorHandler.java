package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoctorHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueBlood.db";
        public DoctorHandler(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE Doctor("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT,"
                    + "Email TEXT,"
                    + "Contact TEXT,"
                    + "Hospital TEXT,"
                    + "Qualification TEXT)";

            // method to execute above sql query
            db.execSQL(query);
        }
        @Override
        public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
            MyDB.execSQL("drop Table if exists Doctor");
        }

    public void AddNewEntry(String Name, String Email, String contact, String Hospital) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", Name);
        values.put("Email", Email);
        values.put("Contact", contact);
        values.put("Hospital", Hospital);

        MyDB.insert("Doctor", null, values);

        MyDB.close();
    }

}
