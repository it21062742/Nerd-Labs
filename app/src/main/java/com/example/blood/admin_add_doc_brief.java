package com.example.blood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;
import Database.DoctorHandler;

public class admin_add_doc_brief extends AppCompatActivity {
   DoctorHandler myDB;
   ArrayList<String> id, name, email, contact, hospital, Qualification;
   RecyclerView recyclerView;
   AdapterFor_doc custAdapter;
    String email1;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_add_doctor_brief);

       recyclerView = (RecyclerView) findViewById(R.id.DocView);

       id = new ArrayList<String>();
       name = new ArrayList<String>();
       email = new ArrayList<String>();
       contact = new ArrayList<String>();
       hospital = new ArrayList<String>();

       DoctorHandler dh = new DoctorHandler(this, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);
       CurrentReqHandler currentReqHandler = new CurrentReqHandler(this, CurrentUser.PresentUser.TABLENAME, null, 1);
       Cursor cursor1 = currentReqHandler.getUser();

       cursor1.moveToNext();

       fetchRecords1();
       custAdapter = new AdapterFor_doc(admin_add_doc_brief.this,this, id,name,email,contact,hospital,Qualification);
       recyclerView.setAdapter(custAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(admin_add_doc_brief.this));
   }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
            recreate();
    }

    void fetchRecords1() {
        DoctorHandler dh = new DoctorHandler(admin_add_doc_brief.this, DoctorHandler.TABLENAME, null, 1);
        Cursor cursor = dh.readData();

        if (cursor!=null && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(1));
                email.add(cursor.getString(2));
                contact.add(cursor.getString(3));
                hospital.add(cursor.getString(4));

            }
        }
    }

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