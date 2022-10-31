package Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InfoBeforeImageHandler extends SQLiteOpenHelper {

    public static final String DBNAME = "BlueBlood.db";

    public InfoBeforeImageHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateInfoBeforeUploadTable = "CREATE TABLE " + DeliveryReqTable.DeliveryReq.TABLENAME +
                "(" + InfoBeforeImage.Info.ID + " INTEGER PRIMARY KEY, " +
                InfoBeforeImage.Info.NAME + " TEXT, " +
                InfoBeforeImage.Info.AREA + " TEXT, " +
                InfoBeforeImage.Info.CONTACT + " TEXT)";

        db.execSQL(CreateInfoBeforeUploadTable);
    }

    public Boolean addRecord(String patientName, String area, String contact) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map

        values.put(InfoBeforeImage.Info.NAME, patientName);
        values.put(InfoBeforeImage.Info.AREA, area);
        values.put(InfoBeforeImage.Info.CONTACT, contact);

        long retVal = db.insert(InfoBeforeImage.Info.TABLENAME, null, values);

        if (retVal != -1)
            return true;
        else
            return false;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DeliveryReqTable.DeliveryReq.TABLENAME + " where " + "ID =?", new String[]{"1"});

        if (cursor.getCount() > 0)
            return cursor;
        else
            return null;
    }

    public Boolean Update(String patientName, String area, String contact) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //This is like a map

        values.put(InfoBeforeImage.Info.NAME, patientName);
        values.put(InfoBeforeImage.Info.AREA, area);
        values.put(InfoBeforeImage.Info.CONTACT, contact);

        long retVal = db.update(InfoBeforeImage.Info.TABLENAME, values, InfoBeforeImage.Info.ID+ " =?", new String[]{"1"});

        if (retVal != -1)
            return true;
        else
            return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
