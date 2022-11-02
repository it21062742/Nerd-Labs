package com.example.blood;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Database.Doctor_request;

public class admin_add_doc_brief extends AppCompatActivity {
    Doctor_request myDB;
    ArrayList<String> id, name, email, contact, hospital;
    RecyclerView recyclerView;
    AdapterFor_doc custAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_brief);

        recyclerView = findViewById(R.id.DocView);

        id = new ArrayList<>();
        name = new ArrayList<>();
        email = new ArrayList<>();
        contact = new ArrayList<>();
        hospital = new ArrayList<>();

        fetchRecords1();
        Log.d("info", id.get(0));
        custAdapter = new AdapterFor_doc(this,admin_add_doc_brief.this,  id, name, email, contact, hospital);
        recyclerView.setAdapter(custAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(admin_add_doc_brief.this));
    }

    void fetchRecords1() {
        Doctor_request dh = new Doctor_request(admin_add_doc_brief.this, Doctor_request.TABLENAME, null, 1);
        Cursor cursor = dh.readData();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                email.add(cursor.getString(2));
                contact.add(cursor.getString(3));
                hospital.add(cursor.getString(4));
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Completed Request History Empty", Toast.LENGTH_SHORT).show();
    }
}