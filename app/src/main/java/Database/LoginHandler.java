package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueBlood.db";
    public LoginHandler(Context context) {
        super(context, DBNAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String CreateDeliveryReqTable = "CREATE TABLE " + DeliveryReqTable.DeliveryReq.TABLENAME +
                "(" + DeliveryReqTable.DeliveryReq.REQID + " INTEGER PRIMARY KEY, " +
                DeliveryReqTable.DeliveryReq.PATIENTNAME + " TEXT, " +
                DeliveryReqTable.DeliveryReq.AREA + " TEXT, " +
                DeliveryReqTable.DeliveryReq.CONTACT + " TEXT, " +
                DeliveryReqTable.DeliveryReq.DATE + " TEXT, " +
                DeliveryReqTable.DeliveryReq.PHARMACYNAME + " TEXT, " +
                DeliveryReqTable.DeliveryReq.EMAIL + " TEXT)";

        // + "FOREIGN  KEY ("+ DeliveryReqTable.DeliveryReq.EMAIL +") REFERENCES users(username) ON DELETE CASCADE ON UPDATE CASCADE)"
        //+ DeliveryReqTable.DeliveryReq.IMAGENAME + " BLOB, "
        MyDB.execSQL(CreateDeliveryReqTable);
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
