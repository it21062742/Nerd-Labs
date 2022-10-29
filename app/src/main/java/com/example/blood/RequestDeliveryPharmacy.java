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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class RequestDeliveryPharmacy extends AppCompatActivity {
    EditText name, area, contact, image;
    Button upImage, submit, ImageUploadBtn;
    TextView uploadLabel;
    Bundle extras;
    byte[] byteArray;

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    Bitmap bmp = null;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PharmacyAll.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_delivery_pharmacy);

//        String email = String.valueOf(getIntent().getStringExtra("email"));
        CurrentReqHandler currentReqHandler = new CurrentReqHandler(this, CurrentUser.PresentUser.TABLENAME, null, 1);

        Cursor cursor = currentReqHandler.getUser();

        cursor.moveToNext();
        String email = cursor.getString(1).trim();

        //For back Button (Also set the parent activity in AdnroidManifest.xml)
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        DeliveryReqHandler dh = new DeliveryReqHandler(this, DeliveryReqTable.DeliveryReq.TABLENAME, null, 1);

        extras = getIntent().getExtras();
        byteArray = extras.getByteArray("image");
        uploadLabel = findViewById(R.id.PrescriptionReqDeliveryLabel);
        ImageUploadBtn = findViewById(R.id.ImageUploadBtn);

        if (getIntent().hasExtra("image")) {
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            uploadLabel.setText("Image Selected*");
            ImageUploadBtn.setText("CHANGE IMAGE");

            uploadLabel.setTextColor(Color.parseColor("#00acc1"));
            ImageUploadBtn.setTextColor(Color.parseColor("#00acc1"));
        }


        submit = findViewById(R.id.Edit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.nameTB);
                area = findViewById(R.id.areaTb);
                contact = findViewById(R.id.contactTB);
                String pharm = mySpinner1.getSelectedItem().toString();

                extras = getIntent().getExtras();
                byteArray = extras.getByteArray("image");

                //To change button color on Upload Image button Click
                if (getIntent().hasExtra("image")) {
                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    uploadLabel.setText("Image Selected");
                    ImageUploadBtn.setText("CHANGE IMAGE");
                }


                if (!name.getText().toString().isEmpty() &&
                        !area.getText().toString().isEmpty() &&
                        !contact.getText().toString().isEmpty() && bmp != null) {

                    Boolean status = dh.addRecord(name.getText().toString().trim(),
                            area.getText().toString().trim(),
                            String.valueOf(contact.getText()).trim(),
                            pharm.trim(), bmp, email);

                    if (status == true)
                        Toast.makeText(RequestDeliveryPharmacy.this, "Request Successful", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(RequestDeliveryPharmacy.this, "Request Failed", Toast.LENGTH_LONG).show();

                    Intent back = new Intent(getApplicationContext(), PharmacyAll.class);
                    startActivity(back);
                } else
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields To Make Request", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moveToImageUploadPage(View view) {
        Intent a = new Intent(getApplicationContext(), PharmacyImageUpload.class);
        startActivity(a);
    }
}