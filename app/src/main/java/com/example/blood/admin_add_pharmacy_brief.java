package com.example.blood;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Database.Pharmacy_request;

public class admin_add_pharmacy_brief extends AppCompatActivity {
    Pharmacy_request myDB;
    ArrayList<String> id, name, email, contact, address;
    RecyclerView recyclerView;
    AdapterFor_Phar custAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_pharmacy_brief);

        recyclerView = findViewById(R.id.PharView);


        id = new ArrayList<>();
        name = new ArrayList<>();
        email = new ArrayList<>();
        contact = new ArrayList<>();
        address = new ArrayList<>();

        fetchRecords();

        custAdapter = new AdapterFor_Phar(this,admin_add_pharmacy_brief.this,  id, name, email, contact, address);
        recyclerView.setAdapter(custAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(admin_add_pharmacy_brief.this));
    }

    void fetchRecords() {
        Pharmacy_request dh = new Pharmacy_request(admin_add_pharmacy_brief.this, Pharmacy_request.TABLENAME, null, 1);
        Cursor cursor = dh.readDataPhar();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                email.add(cursor.getString(2));
                contact.add(cursor.getString(3));
                address.add(cursor.getString(4));
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Completed Request History Empty", Toast.LENGTH_SHORT).show();
    }
}