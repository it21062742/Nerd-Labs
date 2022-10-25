package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PharmacyUpdate extends AppCompatActivity {

    EditText name, area, cont;
    Button edit, confirm;
    String intName, intArea, intCont, intPharm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_update);

        //For back Button (Also set the parent activity in AdnroidManifest.xml)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creating the dropdown list using spinner for Salutation
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerForName);
        //the dropdown list selection are stored in String.xml file in values folder

        //ArrayAdapter is the container that willl hold the values and then integrate them with the spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(PharmacyUpdate.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        //For it to show as dropdownlist
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //To set our spinner to the adapter
        mySpinner.setAdapter(myAdapter);


        //Creating the dropdown list using spinner for Pharmacy Selection
        Spinner mySpinner1 = (Spinner) findViewById(R.id.spinnerForPharmacySelection);
        //the dropdown list selection are stored in String.xml file in values folder

        //ArrayAdapter is the container that willl hold the values and then integrate them with the spinner
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(PharmacyUpdate.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pharmacyList));

        //For it to show as dropdownlist
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //To set our spinner to the adapter
        mySpinner1.setAdapter(myAdapter1);

        name = findViewById(R.id.nameTB);
        area = findViewById(R.id.areaTb);
        cont = findViewById(R.id.contactTB);
        edit = findViewById(R.id.Edit);
        confirm = findViewById(R.id.confirm);

        getAndSetIntentData();
        name.setEnabled(false);
        area.setEnabled(false);
        cont.setEnabled(false);

    }
    void getAndSetIntentData()
    {
        Intent i = getIntent();
        intName = String.valueOf(i.getStringExtra("reqID"));
        intArea = String.valueOf(i.getStringExtra("name"));
        intCont = String.valueOf(i.getStringExtra("pharm"));
        intPharm = String.valueOf(i.getStringExtra("date"));

        name.setText(name.toString());
        area.setText(area.toString());
        cont.setText(cont.toString());
    }
}