package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class PharmacyAll extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addBtn;
    ArrayList<String> PatientName,ReqList,Date, Pharmacy, Area, Contacts;
    PharmacyReqAdapter custAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_all);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RequestDeliveryPharmacy.class);
                Intent i = getIntent();
                String email = i.getStringExtra("email");

                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        PatientName = new ArrayList<>();
        ReqList = new ArrayList<>();
        Date = new ArrayList<>();
        Pharmacy = new ArrayList<>();
        Area = new ArrayList<>();
        Contacts = new ArrayList<>();

        fetchRecords();
        custAdapter = new PharmacyReqAdapter(PharmacyAll.this,ReqList, Date, PatientName, Pharmacy, Contacts, Area);
        recyclerView.setAdapter(custAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PharmacyAll.this));
    }
    void fetchRecords() {
        DeliveryReqHandler dh = new DeliveryReqHandler(this, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);
        Cursor cursor = dh.getData();

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
    }
}