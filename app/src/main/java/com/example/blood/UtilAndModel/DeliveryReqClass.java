package com.example.blood.UtilAndModel;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.blood.PharmacyAll;
import com.example.blood.RequestDeliveryPharmacy;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class DeliveryReqClass {

    private Context context;
    private DeliveryReqHandler dh;

    private ArrayList<String> PatientName, ReqList, Date, Pharmacy, Area, Contacts;

    public DeliveryReqClass(Context context) {
        dh = new DeliveryReqHandler(context, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);
        this.context = context;
    }

    public Cursor getData(String email) {
        Cursor cursor = dh.getData(email);

        return cursor;
    }

    public ArrayList getDataInArrayList(String email, Boolean compstatus) {
        Cursor cursor;

        if (compstatus == true)
            cursor = dh.getCompletedData(email);
        else
            cursor = dh.getData(email);

        ArrayList<ArrayList> All = new ArrayList();
        PatientName = new ArrayList<>();
        ReqList = new ArrayList<>();
        Date = new ArrayList<>();
        Pharmacy = new ArrayList<>();
        Area = new ArrayList<>();
        Contacts = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                PatientName.add(cursor.getString(1));
                ReqList.add(cursor.getString(0));
                Date.add(cursor.getString(4));
                Pharmacy.add(cursor.getString(5));
                Area.add(cursor.getString(2));
                Contacts.add(cursor.getString(3));
            }
        }

        All.add((ArrayList) PatientName);
        All.add((ArrayList) ReqList);
        All.add((ArrayList) Date);
        All.add((ArrayList) Pharmacy);
        All.add((ArrayList) Area);
        All.add((ArrayList) Contacts);
        return All;
    }

    public Boolean setData(String name, String area, String contact, String pharmName, Bitmap image, String email) {
        Boolean validation = validator(name, area, contact, image);

        if (validation == true) {
            Boolean result = dh.addRecord(name, area, contact, pharmName, image, email);
            if (result == true) {
                Toast.makeText(context, "Request Made Successfully", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(context, "Request Failed", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else
            return false;
    }

    public Boolean validator(String name, String area, String contact, Bitmap image) {
        if (!name.isEmpty() && !area.isEmpty() && !contact.isEmpty()) {
            if(image==null)
            {
                Toast.makeText(context, "Please Upload The Image Of Your Prescription", Toast.LENGTH_LONG).show();
                return false;
            }
            if (!Pattern.matches("[a-z A-Z]+", name)) {
                Toast.makeText(context, "Name Cannot Contain Numbers", Toast.LENGTH_LONG).show();
                return false;
            }
            if (name.length() < 3)
            {
                Toast.makeText(context, "Name Must Be Atleast 3 Characters Long", Toast.LENGTH_LONG).show();
                return false;
            }

            if (area.length() < 5)
            {
                Toast.makeText(context, "Area Must Contain Atleast 5 Characters", Toast.LENGTH_LONG).show();
                return false;
            }

            if (contact.length() == 10) {
                if (contact.length() == 10 && !contact.substring(0, 1).equals("0")) {
                    Toast.makeText(context, "Invalid Contact Number", Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                Toast.makeText(context, "Contact Number Must Contain Exactly 10 Digits", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(context, "Please Fill All Fields To Proceed", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public Boolean update(String patientName, String area, String contact, String pharmName, String email) {
        Boolean validation = updateValidator(patientName, area, contact);

        if (validation == true) {
            Boolean result = dh.Update(patientName, area, contact, pharmName, email);
            if (result == true) {
                Toast.makeText(context, "Update Successful", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(context, "Cannot Update At The Moment. Please Try Again Later", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else
            return false;
    }

    public Boolean updateValidator(String name, String area, String contact) {
        if (!name.isEmpty() && !area.isEmpty() && !contact.isEmpty()) {
            if (!Pattern.matches("[a-z A-Z]+", name)) {
                Toast.makeText(context, "Name Cannot Contain Any Numbers", Toast.LENGTH_LONG).show();
                return false;
            }
            if (name.length() < 3)
            {
                Toast.makeText(context, "Name Must Be Atleast 3 Characters Long", Toast.LENGTH_LONG).show();
                return false;
            }

            if (area.length() < 5)
            {
                Toast.makeText(context, "Area Must Contain Atleast 5 Characters", Toast.LENGTH_LONG).show();
                return false;
            }

            if (contact.length() == 10) {
                if (contact.length() == 10 && !contact.substring(0, 1).equals("0")) {
                    Toast.makeText(context, "Invalid Contact Number", Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                Toast.makeText(context, "Invalid Contact Number. Contact Number Must Contain Exactly 10 Digits", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(context, "Please Fill All Fields To Proceed", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
        Boolean validation = updateValidator(patientName, area, contact);

        if (validation == true) {
            Boolean result = dh.UpdateOnID(patientName, area, contact, pharmName, reqID);
            if (result == true) {
                Toast.makeText(context, "Update Successful", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(context, "Cannot Update At The Moment. Please Try Again Later", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else
            return false;
    }

    public void updateStatusToComplete(String reqID) {
        Boolean result = dh.updateStatusToComplete(reqID);
    }

    public Boolean DeleteOneRow(String reqID) {
        return dh.DeleteOneRow(reqID);
    }

    public Boolean DeleteAll(String email) {
        return dh.DeleteAll(email);
    }

    public byte[] imageViewToByte(Bitmap imageToStore) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageInBytes = byteArrayOutputStream.toByteArray();
        return imageInBytes;
    }

    public int noRecordsInPharmacyAll()
    {
        Cursor cursor = dh.getAllRecords();

        if(cursor!=null)
            return cursor.getCount();
        else
            return 0;
    }
}
