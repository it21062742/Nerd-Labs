package Database;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoctorHandler extends SQLiteOpenHelper {
    public static final String TABLENAME ="Doctor";
    public static final String DBNAME = "BlueBlood.db";

        public DoctorHandler(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE "+TABLENAME+" ("
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
        if(result==-1) return false;
        else
            return true;
    }
    public Cursor readData(){
            String querry = "SELECT * FROM "+TABLENAME;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = null;
            if(db != null){
                cursor = db.rawQuery(querry,null);
            }
            return cursor;
    }
    public Boolean DeleteOneRow(String reqID) {
        SQLiteDatabase MyDB = getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where ID =?", new String[]{reqID});

        if (cursor.getCount() > 0) { //To check the number of records>0 in table
            long retVal = MyDB.delete(DeliveryReqTable.DeliveryReq.TABLENAME, DeliveryReqTable.DeliveryReq.REQID + " =?", new String[]{reqID});

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

        Cursor cursor = db.rawQuery("Select * from " +TABLENAME + " where ID =?", new String[]{ID});

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



