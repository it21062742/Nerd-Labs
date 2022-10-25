package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class RequestDeliveryPharmacy extends AppCompatActivity {
    EditText name, area, contact, image;
    Button upImage, submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_delivery_pharmacy);

        Intent intent = getIntent();
        String email = String.valueOf(intent.getStringExtra("email"));

        //For back Button (Also set the parent activity in AdnroidManifest.xml)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creating the dropdown list using spinner for Salutation
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerForName);
        //the dropdown list selection are stored in String.xml file in values folder

        //ArrayAdapter is the container that willl hold the values and then integrate them with the spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RequestDeliveryPharmacy.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        //For it to show as dropdownlist
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //To set our spinner to the adapter
        mySpinner.setAdapter(myAdapter);


        //Creating the dropdown list using spinner for Pharmacy Selection
        Spinner mySpinner1 = (Spinner) findViewById(R.id.spinnerForPharmacySelection);
        //the dropdown list selection are stored in String.xml file in values folder

        //ArrayAdapter is the container that willl hold the values and then integrate them with the spinner
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(RequestDeliveryPharmacy.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pharmacyList));

        //For it to show as dropdownlist
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //To set our spinner to the adapter
        mySpinner1.setAdapter(myAdapter1);

        DeliveryReqHandler dh = new DeliveryReqHandler(this, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);

        submit = findViewById(R.id.saveButtonEditRequest);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.enterNameTBEditRequest);
                area = findViewById(R.id.enterAreaTbEditRequest);
                contact = findViewById(R.id.enterContactTBEditRequest);
                String pharm = mySpinner1.getSelectedItem().toString();

                Boolean status = dh.addRecord(name.getText().toString(), area.getText().toString(), String.valueOf(contact.getText()), pharm);

                if (status == true)
                    Toast.makeText(RequestDeliveryPharmacy.this, "Success", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RequestDeliveryPharmacy.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}