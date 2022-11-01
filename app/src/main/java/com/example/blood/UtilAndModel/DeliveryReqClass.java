package com.example.blood.UtilAndModel;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.blood.RequestDeliveryPharmacy;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class DeliveryReqClass {

    private Context context;
    private DeliveryReqHandler dh;
    private String patName, area, contact, date, pharmName, status, email;
    private Bitmap image;

    private ArrayList<String> PatientName,ReqList,Date, Pharmacy, Area, Contacts;

    public DeliveryReqClass(Context context) {
        dh = new DeliveryReqHandler(context, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);
        this.context = context;
    }

    public Cursor getData(String email) {
        Cursor cursor = dh.getData(email);

        return cursor;
    }

    public ArrayList getDataInArrayList(String email)
    {
        Cursor cursor = dh.getCompletedData(email);

        ArrayList<ArrayList> All = new ArrayList();
        PatientName = new ArrayList<>();
        ReqList = new ArrayList<>();
        Date = new ArrayList<>();
        Pharmacy = new ArrayList<>();
        Area = new ArrayList<>();
        Contacts = new ArrayList<>();

        if (cursor!=null) {
            while (cursor.moveToNext()) {
                PatientName.add(cursor.getString(1));
                ReqList.add(cursor.getString(0));
                Date.add(cursor.getString(4));
                Pharmacy.add(cursor.getString(5));
                Area.add(cursor.getString(2));
                Contacts.add(cursor.getString(3));
            }
                All.add((ArrayList) PatientName);
                All.add((ArrayList)ReqList);
                All.add((ArrayList)Date);
                All.add((ArrayList)Pharmacy);
                All.add((ArrayList)Area);
                All.add((ArrayList)Contacts);
            }
        return All;
    }

    public Boolean setData(String name, String area, String contact, String pharmName, Bitmap image, String email) {
        Boolean result = dh.addRecord(name, area, contact, pharmName, image, email);

        if (result == true)
            return true;
        else
            return false;
    }

    public void update(String patientName, String area, String contact, String pharmName, String email) {
        Boolean result = dh.Update(patientName, area, contact, pharmName, email);
    }

    public Cursor getImageOnId(String reqID) {
        Cursor cursor = dh.getImageOnID(reqID);
        return cursor;
    }

    public Cursor getCompData(String email) {
        Cursor cursor = dh.getCompletedData(email);
        return cursor;
    }

    public Boolean UpdateOnID(String patientName, String area, String contact, String pharmName, String reqID) {
        Boolean result = dh.UpdateOnID(patientName, area, contact, pharmName, reqID);

        return result;
    }

    public void updateStatusToComplete(String reqID) {
        Boolean result = dh.updateStatusToComplete(reqID);
    }

    public Boolean DeleteOneRow(String reqID) {
        return dh.DeleteOneRow(reqID);
    }

    public byte[] imageViewToByte(Bitmap imageToStore)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte [] imageInBytes = byteArrayOutputStream.toByteArray();
        return imageInBytes;
    }
}
