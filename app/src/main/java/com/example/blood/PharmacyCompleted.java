package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.blood.UtilAndModel.DeliveryReqClass;
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
        DeliveryReqClass dr = new DeliveryReqClass(getApplicationContext());

        ArrayList All = dr.getDataInArrayList(email);
        PatientName = new ArrayList<>();
        ReqList = new ArrayList<>();
        Date = new ArrayList<>();
        Pharmacy = new ArrayList<>();
        Area = new ArrayList<>();
        Contacts = new ArrayList<>();

        if(All.size()>0)
        {
            PatientName = (ArrayList<String>) All.get(0);
            ReqList = (ArrayList<String>) All.get(1);
            Date = (ArrayList<String>) All.get(2);
            Pharmacy = (ArrayList<String>) All.get(3);
            Area = (ArrayList<String>) All.get(4);
            Contacts = (ArrayList<String>) All.get(5);
        }
        else
            Toast.makeText(getApplicationContext(), "Completed Request History Empty", Toast.LENGTH_SHORT).show();
    }
}