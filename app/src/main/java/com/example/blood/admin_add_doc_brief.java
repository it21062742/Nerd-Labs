package com.example.blood;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Database.DoctorHandler;

public class admin_add_doc_brief extends AppCompatActivity {
   DoctorHandler myDB;
   ArrayList<String> id, name, email, contact, hospital;
   RecyclerView recyclerView;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_add_doctor_brief);
       recyclerView = (RecyclerView) findViewById(R.id.DocView);

       myDB = new DoctorHandler(admin_add_doc_brief.this);
       id = new ArrayList<String>();
       name = new ArrayList<String>();
       email = new ArrayList<String>();
       contact = new ArrayList<String>();
       hospital = new ArrayList<String>();
   }

   void StoreDataInArray() {
       Cursor cursor = myDB.readData();
       if (cursor.getCount() == 0) {
           Toast.makeText(this, "No request available", Toast.LENGTH_SHORT).show();
       } else {
           while (cursor.moveToFirst()) {
               id.add(cursor.getString(0));
               name.add(cursor.getString(1));
               email.add(cursor.getString(2));
               contact.add(cursor.getString(3));
               hospital.add(cursor.getString(4));
           }
       }
   }

}