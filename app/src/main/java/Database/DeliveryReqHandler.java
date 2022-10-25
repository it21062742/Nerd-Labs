package Database;

import static android.content.ContentValues.TAG;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DeliveryReqHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueBlood.db";
    ContentResolver mResolver;

    public DeliveryReqHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BLOB is the data type to store images
        String CreateDeliveryReqTable = "CREATE TABLE " + DeliveryReqTable.DeliveryReq.TABLENAME + "(" + DeliveryReqTable.DeliveryReq.REQID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DeliveryReqTable.DeliveryReq.PATIENTNAME + " TEXT, " + DeliveryReqTable.DeliveryReq.AREA + " TEXT, " + DeliveryReqTable.DeliveryReq.CONTACT + " TEXT, " + DeliveryReqTable.DeliveryReq.DATE + " TEXT, " + DeliveryReqTable.DeliveryReq.PHARMACYNAME + " TEXT, " + DeliveryReqTable.DeliveryReq.EMAIL + " TEXT)";
        // + "FOREIGN  KEY ("+ DeliveryReqTable.DeliveryReq.EMAIL +") REFERENCES users(username) ON DELETE CASCADE ON UPDATE CASCADE)"
        //+ DeliveryReqTable.DeliveryReq.IMAGENAME + " BLOB, "
        db.execSQL(CreateDeliveryReqTable);
    }

    public long addRecord(String patientName, String area, String contact, String pharmName) {
        //byte [] image, String email
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map

        //To get the date
//        Date c = Calendar.getInstance().getTime();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        String formattedDate = df.format(c);

        values.put(DeliveryReqTable.DeliveryReq.PATIENTNAME, patientName);
        values.put(DeliveryReqTable.DeliveryReq.AREA, area);
        values.put(DeliveryReqTable.DeliveryReq.CONTACT, contact);
//        values.put(DeliveryReqTable.DeliveryReq.IMAGENAME, image);
        values.put(DeliveryReqTable.DeliveryReq.DATE, "formattedDate");
        values.put(DeliveryReqTable.DeliveryReq.PHARMACYNAME, pharmName);
//        values.put(DeliveryReqTable.DeliveryReq.EMAIL, email);

        long retVal = db.insert(DeliveryReqTable.DeliveryReq.TABLENAME, null, values);
        return retVal;
    }

    //
//    public List readAllInfo() {
//        SQLiteDatabase db = getReadableDatabase();
//
//        String[] colNames = {
//                UserMaster.Users.COL1,
//                UserMaster.Users.COL2
//        };
//
//        String orderBy = UserMaster.Users.USERID + " DESC";
//
//        Cursor cursor = db.query(
//                UserMaster.Users.TABLENAME, colNames, null, null, null, null, orderBy
//        );
//
//        List UserNames = new ArrayList<>();
//        List Passwords = new ArrayList<>();
//        List All = new ArrayList<>();
//        while (cursor.moveToNext()) {
//            String uName = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COL1));
//            String pwd = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COL2));
//            UserNames.add(uName);
//            Passwords.add(pwd);
//        }
//
//        All.add(UserNames);
//        All.add(Passwords);
//
//        return All;
//    }
//
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS " + DeliveryReqTable.DeliveryReq.TABLENAME);
    }
}
