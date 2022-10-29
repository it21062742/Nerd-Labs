package Database;

import static android.content.ContentValues.TAG;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DeliveryReqHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueBlood.db";
    CurrentReqHandler currentReqHandler;
    ContentResolver mResolver;

    public DeliveryReqHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, 1); //choose 1st constructor and do this inside of it
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BLOB is the data type to store images
        String CreateDeliveryReqTable = "CREATE TABLE " + DeliveryReqTable.DeliveryReq.TABLENAME +
                "(" + DeliveryReqTable.DeliveryReq.REQID + " INTEGER PRIMARY KEY, " +
                DeliveryReqTable.DeliveryReq.PATIENTNAME + " TEXT, " +
                DeliveryReqTable.DeliveryReq.AREA + " TEXT, " +
                DeliveryReqTable.DeliveryReq.CONTACT + " TEXT, " +
                DeliveryReqTable.DeliveryReq.DATE + " TEXT, " +
                DeliveryReqTable.DeliveryReq.PHARMACYNAME + " TEXT, " +
                DeliveryReqTable.DeliveryReq.STATUS + " TEXT, " +
                DeliveryReqTable.DeliveryReq.IMAGENAME + " BLOB, " +
                DeliveryReqTable.DeliveryReq.EMAIL + " TEXT)";

        // + "FOREIGN  KEY ("+ DeliveryReqTable.DeliveryReq.EMAIL +") REFERENCES users(username) ON DELETE CASCADE ON UPDATE CASCADE)"
        //+ DeliveryReqTable.DeliveryReq.IMAGENAME + " BLOB, "
        db.execSQL(CreateDeliveryReqTable);
    }

    public Boolean addRecord(String patientName, String area, String contact, String pharmName, Bitmap image, String email) {
        //byte [] image, String email
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map

        //To get the date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        //Converting image to suitable format to store in DB
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap imageBitmap = (Bitmap) image;
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte [] imageInBytes = byteArrayOutputStream.toByteArray();

        values.put(DeliveryReqTable.DeliveryReq.PATIENTNAME, patientName);
        values.put(DeliveryReqTable.DeliveryReq.AREA, area);
        values.put(DeliveryReqTable.DeliveryReq.CONTACT, contact);
        values.put(DeliveryReqTable.DeliveryReq.IMAGENAME, imageInBytes);
        values.put(DeliveryReqTable.DeliveryReq.DATE, formattedDate);
        values.put(DeliveryReqTable.DeliveryReq.PHARMACYNAME, pharmName);
        values.put(DeliveryReqTable.DeliveryReq.STATUS, "Ongoing");
        values.put(DeliveryReqTable.DeliveryReq.EMAIL, email);

        long retVal = db.insert(DeliveryReqTable.DeliveryReq.TABLENAME, null, values);

        if (retVal != -1)
            return true;
        else
            return false;
    }

    public Cursor getData(String email) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DeliveryReqTable.DeliveryReq.TABLENAME + " where " + DeliveryReqTable.DeliveryReq.STATUS + " =? AND UserEmail =?", new String[]{"Ongoing", email});

        if (cursor.getCount() > 0)
            return cursor;
        else
            return null;
    }

    public Cursor getImageOnID(String reqID)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DeliveryReqTable.DeliveryReq.TABLENAME + " where " + DeliveryReqTable.DeliveryReq.STATUS + " =? AND reqID =?", new String[]{"Ongoing", reqID});

        if (cursor.getCount() > 0)
            return cursor;
        else
            return null;
    }

    public Cursor getCompletedData(String email) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DeliveryReqTable.DeliveryReq.TABLENAME + " where " + DeliveryReqTable.DeliveryReq.STATUS + " =? AND UserEmail =?", new String[]{"Completed", email});

        if (cursor.getCount() > 0)
            return cursor;
        else
            return null;
    }

    public Boolean Update(String patientName, String area, String contact, String pharmName, String email) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map

        values.put(DeliveryReqTable.DeliveryReq.PATIENTNAME, patientName);
        values.put(DeliveryReqTable.DeliveryReq.AREA, area);
        values.put(DeliveryReqTable.DeliveryReq.CONTACT, contact);
//        values.put(DeliveryReqTable.DeliveryReq.IMAGENAME, image);
        values.put(DeliveryReqTable.DeliveryReq.PHARMACYNAME, pharmName);

        Cursor cursor = db.rawQuery("Select * from " + DeliveryReqTable.DeliveryReq.TABLENAME + " where " + DeliveryReqTable.DeliveryReq.EMAIL + " =?", new String[]{email});

        if (cursor.getCount() > 0) {
            long retVal = db.update(DeliveryReqTable.DeliveryReq.TABLENAME, values, DeliveryReqTable.DeliveryReq.EMAIL + " =?", new String[]{email});

            if (retVal != -1)
                return true;
            else
                return false;
        } else
            return false;
    }

    public Boolean UpdateOnID(String patientName, String area, String contact, String pharmName, String reqID) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map

        values.put(DeliveryReqTable.DeliveryReq.PATIENTNAME, patientName);
        values.put(DeliveryReqTable.DeliveryReq.AREA, area);
        values.put(DeliveryReqTable.DeliveryReq.CONTACT, contact);
        values.put(DeliveryReqTable.DeliveryReq.PHARMACYNAME, pharmName);

        Cursor cursor = db.rawQuery("Select * from " + DeliveryReqTable.DeliveryReq.TABLENAME + " where " + DeliveryReqTable.DeliveryReq.REQID + " =?", new String[]{reqID});

        if (cursor.getCount() > 0) {
            long retVal = db.update(DeliveryReqTable.DeliveryReq.TABLENAME, values, DeliveryReqTable.DeliveryReq.REQID + " =?", new String[]{reqID});

            if (retVal != -1) return true;
            else return false;
        } else return false;
    }

    public Boolean updateStatusToComplete(String reqID) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DeliveryReqTable.DeliveryReq.STATUS, "Completed");
        long retVal = db.update(DeliveryReqTable.DeliveryReq.TABLENAME, values, DeliveryReqTable.DeliveryReq.REQID + " =?", new String[]{reqID});

        if (retVal == 1) return true;
        else return false;
    }

    public Boolean DeleteOneRow(String reqID) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from " + DeliveryReqTable.DeliveryReq.TABLENAME + " where " + DeliveryReqTable.DeliveryReq.REQID + " =?", new String[]{reqID});

        if (cursor.getCount() > 0) { //To check the number of records>0 in table
            long retVal = db.delete(DeliveryReqTable.DeliveryReq.TABLENAME, DeliveryReqTable.DeliveryReq.REQID + " =?", new String[]{reqID});

            if (retVal != -1) return true;
            else return false;
        } else return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DeliveryReqTable.DeliveryReq.TABLENAME);
    }

    public void storeImage()
    {

    }
}
