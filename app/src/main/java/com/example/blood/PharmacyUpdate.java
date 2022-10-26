package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class PharmacyUpdate extends AppCompatActivity {

    EditText name, area, cont;
    Button edit, confirm;
    String intName, intCont, intPharm, intArea;
    String intReq;
    Boolean clicked;

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
        Spinner mySpinner1 = (Spinner) findViewById(R.id.spinnerForPharmacySel);
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
        Log.d("id", intReq);
        Log.d("name", intName);
        Log.d("intCont", intCont);
        Log.d("intArea", intArea);
        //To make EditText readOnly
        name.setFocusable(false);
        area.setFocusable(false);
        cont.setFocusable(false);

        clicked = false;

        DeliveryReqHandler dh = new DeliveryReqHandler(this, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true; // whn edit button pressed. //Now confirm button is save button

                //To allow EditText fields to be edittable
                name.setFocusable(true);
                area.setFocusable(true);
                cont.setFocusable(true);

                confirm.setText("SAVE CHANGES");
                edit.setVisibility(View.GONE);

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clicked == true) {
                            if (name.getText().toString().isEmpty() ||
                                    area.getText().toString().isEmpty() ||
                                    cont.getText().toString().isEmpty()
                            ) {
                                Toast.makeText(getApplicationContext(), "Please Fill Out All Fields", Toast.LENGTH_LONG).show();
                            } else {
                                String pharm = mySpinner1.getSelectedItem().toString();

                                Boolean updateStatus = dh.UpdateOnID(name.getText().toString(), area.getText().toString(),
                                        cont.getText().toString(), pharm, intReq);

                                if (updateStatus == true) {
                                    Toast.makeText(getApplicationContext(), "Update Sucessful", Toast.LENGTH_SHORT).show();
                                    clicked = false;
                                    Intent i1 = new Intent(getApplicationContext(), PharmacyAll.class);
                                    startActivity(i1);
                                } else
                                    Toast.makeText(getApplicationContext(), "Cannot Update At The Moment. Please Try Again Later", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked == false) {
                    Toast.makeText(getApplicationContext(), "Delivery Confirmed. Thank You For Using Our Services", Toast.LENGTH_LONG).show();

                    Intent i1 = new Intent(getApplicationContext(), PharmacyAll.class);
                    startActivity(i1);
                }
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("reqID") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("pharm") &&
                getIntent().hasExtra("date")) {
            intReq = String.valueOf(getIntent().getStringExtra("reqID"));
            intName = String.valueOf(getIntent().getStringExtra("name"));
            intCont = String.valueOf(getIntent().getStringExtra("cont"));
//            intPharm = String.valueOf(getIntent().getStringExtra("date"));
            intArea = String.valueOf(getIntent().getStringExtra("area"));

            name.setText(intName.toString());
            area.setText(intArea.toString());
            cont.setText(intCont.toString());
        }
    }
}