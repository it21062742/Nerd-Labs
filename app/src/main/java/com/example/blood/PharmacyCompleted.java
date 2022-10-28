package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class PharmacyCompleted extends AppCompatActivity {
    RecyclerView recyclerViewComp;
    ArrayList<String> PatientName,ReqList,Date, Pharmacy, Area, Contacts;
    PharmacyCompletedAdapter custAdapter;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_completed);

        PatientName = new ArrayList<>();
        ReqList = new ArrayList<>();
        Date = new ArrayList<>();
        Pharmacy = new ArrayList<>();
        Area = new ArrayList<>();
        Contacts = new ArrayList<>();

        CurrentReqHandler currentReqHandler = new CurrentReqHandler(this, CurrentUser.PresentUser.TABLENAME, null, 1);
        Cursor cursor1 = currentReqHandler.getUser();

        cursor1.moveToNext();
        email = cursor1.getString(1).trim();
        fetchRecords();

        custAdapter = new PharmacyCompletedAdapter(this, PharmacyCompleted.this, ReqList, Date, PatientName, Pharmacy, Contacts, Area);
        recyclerViewComp = findViewById(R.id.recyclerViewComp);
        recyclerViewComp.setAdapter(custAdapter);
        recyclerViewComp.setLayoutManager(new LinearLayoutManager(PharmacyCompleted.this));
    }
    void fetchRecords() {
        DeliveryReqHandler dh = new DeliveryReqHandler(PharmacyCompleted.this, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);
        Cursor cursor = dh.getCompletedData(email);

        if (cursor!=null && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                PatientName.add(cursor.getString(1));
                ReqList.add(cursor.getString(0));
                Date.add(cursor.getString(4));
                Pharmacy.add(cursor.getString(5));
                Area.add(cursor.getString(2));
                Contacts.add(cursor.getString(3));
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Completed Request History Empty", Toast.LENGTH_SHORT).show();
    }
}