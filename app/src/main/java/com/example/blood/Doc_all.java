package com.example.blood;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Database.Doctor_request;

public class Doc_all extends AppCompatActivity {
    Doctor_request myDB;
    ArrayList<String> id, name, email, contact, hospital;
    RecyclerView recyclerView;
    AdapterFor_doc custAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_all);


            recyclerView = findViewById(R.id.Doc_all);

            id = new ArrayList<>();
            name = new ArrayList<>();
            email = new ArrayList<>();
            contact = new ArrayList<>();
            hospital = new ArrayList<>();

            fetchRecords1();
            custAdapter = new AdapterFor_doc(this,Doc_all.this,  id, name, email, contact, hospital);
            recyclerView.setAdapter(custAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Doc_all.this));
        }

        void fetchRecords1() {
            Doctor_request dh = new Doctor_request(Doc_all.this, Doctor_request.TABLENAME, null, 1);
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
                Toast.makeText(getApplicationContext(), "No Records Found", Toast.LENGTH_SHORT).show();
        }
}