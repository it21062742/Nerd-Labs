package com.example.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.blood.UtilAndModel.ActiveUserClass;
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
    DeliveryReqClass dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_completed);

        ActiveUserClass activeUser = new ActiveUserClass(getApplicationContext());
        email = activeUser.getEmail();
        fetchRecords();

        custAdapter = new PharmacyCompletedAdapter(this, PharmacyCompleted.this, ReqList,
                Date, PatientName, Pharmacy, Contacts, Area);
        recyclerViewComp = findViewById(R.id.recyclerViewComp);
        recyclerViewComp.setAdapter(custAdapter);
        recyclerViewComp.setLayoutManager(new LinearLayoutManager(PharmacyCompleted.this));
    }

    void fetchRecords() {
        dr = new DeliveryReqClass(getApplicationContext());

        ArrayList All = dr.getDataInArrayList(email, true);

        PatientName = (ArrayList<String>) All.get(0);
        ReqList = (ArrayList<String>) All.get(1);
        Date = (ArrayList<String>) All.get(2);
        Pharmacy = (ArrayList<String>) All.get(3);
        Area = (ArrayList<String>) All.get(4);
        Contacts = (ArrayList<String>) All.get(5);

        if(All.size()==0)
            Toast.makeText(getApplicationContext(), "There Are No Completed Requests Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pharmacy_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Cancel) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clear History");
        builder.setMessage("Do You Wish To Clear History? ");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                boolean deleteStatus = dr.DeleteAll(email);

                if (deleteStatus == true)
                    Toast.makeText(getApplicationContext(),
                            "Request Deleted Successfully",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),
                            "error: 404.. Please Try again later",
                            Toast.LENGTH_SHORT).show();

                Intent ii = new Intent(getApplicationContext(), PharmacyAll.class);
                startActivity(ii);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}