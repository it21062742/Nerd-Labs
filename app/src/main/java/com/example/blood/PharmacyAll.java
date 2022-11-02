package com.example.blood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.blood.UtilAndModel.DeliveryReqClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class PharmacyAll extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addBtn;
    ArrayList<String> PatientName,ReqList,Date, Pharmacy, Area, Contacts;
    PharmacyReqAdapter custAdapter;
    String email;
    DeliveryReqClass dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_all);

        recyclerView = findViewById(R.id.recyclerViewComp);
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

        CurrentReqHandler currentReqHandler = new CurrentReqHandler(this, CurrentUser.PresentUser.TABLENAME, null, 1);
        Cursor cursor1 = currentReqHandler.getUser();

        cursor1.moveToNext();
        email = cursor1.getString(1).trim();

        fetchRecords();
        custAdapter = new PharmacyReqAdapter(PharmacyAll.this,this, ReqList, Date, PatientName, Pharmacy, Contacts, Area);
        recyclerView.setAdapter(custAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PharmacyAll.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
            recreate();
    }

    void fetchRecords() {
        dr = new DeliveryReqClass(getApplicationContext());

        ArrayList All = dr.getDataInArrayList(email, false);

        PatientName = (ArrayList<String>) All.get(0);
        ReqList = (ArrayList<String>) All.get(1);
        Date = (ArrayList<String>) All.get(2);
        Pharmacy = (ArrayList<String>) All.get(3);
        Area = (ArrayList<String>) All.get(4);
        Contacts = (ArrayList<String>) All.get(5);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.completed_pharm_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Completed) {
            Intent i = new Intent(getApplicationContext(), PharmacyCompleted.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}