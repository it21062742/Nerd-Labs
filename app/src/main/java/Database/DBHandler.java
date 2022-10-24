//package Database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBHandler extends SQLiteOpenHelper {
//    public static final String DBNAME = "BlueBlood.db";
//
//    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DBNAME, null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CreateDeliveryReqTable = "CREATE TABLE " + DeliveryReqTable.DeliveryReq.TABLENAME + " (" + DeliveryReqTable.DeliveryReq.REQID + " INTEGER PRIMARY KEY, " + DeliveryReqTable.DeliveryReq.PatientName + " Text, " + DeliveryReqTable.DeliveryReq.AREA + " Text, " + DeliveryReqTable.DeliveryReq.CONTACT + " Text, " + DeliveryReqTable.DeliveryReq.IMAGE + " TEXT, " + DeliveryReqTable.DeliveryReq.DATE + " TEXT, " + DeliveryReqTable.DeliveryReq.PHARMACYNAME + " TEXT, " + DeliveryReqTable.DeliveryReq.EMAIL + "TEXT)";
//        db.execSQL(CreateDeliveryReqTable);
//    }
//
////    public void addRecord(String un, String pwd) {
////        SQLiteDatabase db = getWritableDatabase();
////
////        ContentValues values = new ContentValues(); //This is like a map
////
////        values.put(UserMaster.Users.COL1, un);
////        values.put(UserMaster.Users.COL2, pwd);
////
////        long retVal = db.insert(UserMaster.Users.TABLENAME, null, values);
////    }
////
////    public List readAllInfo() {
////        SQLiteDatabase db = getReadableDatabase();
////
////        String[] colNames = {
////                UserMaster.Users.COL1,
////                UserMaster.Users.COL2
////        };
////
////        String orderBy = UserMaster.Users.USERID + " DESC";
////
////        Cursor cursor = db.query(
////                UserMaster.Users.TABLENAME, colNames, null, null, null, null, orderBy
////        );
////
////        List UserNames = new ArrayList<>();
////        List Passwords = new ArrayList<>();
////        List All = new ArrayList<>();
////        while (cursor.moveToNext()) {
////            String uName = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COL1));
////            String pwd = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COL2));
////            UserNames.add(uName);
////            Passwords.add(pwd);
////        }
////
////        All.add(UserNames);
////        All.add(Passwords);
////
////        return All;
////    }
////
////    @Override
////    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
////
////    }
//}
