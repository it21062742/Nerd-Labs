package com.example.blood;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blood.UtilAndModel.ActiveUserClass;
import com.example.blood.UtilAndModel.DeliveryReqClass;
import com.example.blood.UtilAndModel.InfoBeforeImageHandlerModel;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class RequestDeliveryPharmacy extends AppCompatActivity {
    EditText name, area, contact;
    Button submit, ImageUploadBtn;
    TextView uploadLabel;
    Bundle extras;
    byte[] byteArray;
    InfoBeforeImageHandlerModel info;
    Bitmap bmp = null;
    DeliveryReqClass dh;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PharmacyAll.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_delivery_pharmacy);


        info = new InfoBeforeImageHandlerModel(getApplicationContext());

        //To get the email of the active User
        ActiveUserClass activeUser = new ActiveUserClass(getApplicationContext());
        String email = activeUser.getEmail();

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
        Spinner mySpinner1 = (Spinner) findViewById(R.id.spinnerForPharmacySel);
        //the dropdown list selection are stored in String.xml file in values folder

        //ArrayAdapter is the container that willl hold the values and then integrate them with the spinner
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(RequestDeliveryPharmacy.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pharmacyList));

        //For it to show as dropdownlist
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //To set our spinner to the adapter
        mySpinner1.setAdapter(myAdapter1);

        //To set TextViews and Buttons
        setFields();

        //To get and set Intent
        getAndSetIntent();

        submit = findViewById(R.id.Edit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pharm = mySpinner1.getSelectedItem().toString();

                dh = new DeliveryReqClass(getApplicationContext());

                Boolean retVal = dh.setData(name.getText().toString().trim(),
                        area.getText().toString().trim(),
                        String.valueOf(contact.getText()).trim(),
                        pharm.trim(), bmp, email);

                if (retVal == true) {
                    //To redirect to PharmacyAll page once a request is made successfully
                    Intent i = new Intent(getApplicationContext(), PharmacyAll.class);
                    startActivity(i);
                }
            }
        });
    }

    public void moveToImageUploadPage(View view) {
        Intent a = new Intent(getApplicationContext(), PharmacyImageUpload.class);

        //To check and update
        info.checkAndUpdate(name.getText().toString(), area.getText().toString(), contact.getText().toString());
        startActivity(a);
    }

    public void setFields() {
        name = findViewById(R.id.nameTB);
        area = findViewById(R.id.areaTb);
        contact = findViewById(R.id.contactTB);
        uploadLabel = findViewById(R.id.PrescriptionReqDeliveryLabel);
        ImageUploadBtn = findViewById(R.id.ImageUploadBtn);
    }

    //To check if we came from the image upload page with image and then store in database
    public void getAndSetIntent() {
        extras = getIntent().getExtras();
        byteArray = extras.getByteArray("image");

        //To change button color on Upload Image button Click
        if (getIntent().hasExtra("image")) {
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            uploadLabel.setText("Image Selected");
            ImageUploadBtn.setText("CHANGE IMAGE");

            uploadLabel.setTextColor(Color.parseColor("#00acc1"));
            ImageUploadBtn.setTextColor(Color.parseColor("#00acc1"));

            Cursor cursor = info.getData();

            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToNext();

                    name.setText(String.valueOf(cursor.getString(1)));
                    area.setText(String.valueOf(cursor.getString(2)));
                    contact.setText(String.valueOf(cursor.getString(3)));
                }
            }
        }
    }
}