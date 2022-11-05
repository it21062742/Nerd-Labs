package com.example.blood;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blood.UtilAndModel.ActiveUserClass;
import com.example.blood.UtilAndModel.DeliveryReqClass;
import com.example.blood.UtilAndModel.InfoBeforeImageHandlerModel;

import java.util.ArrayList;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;
import Database.Pharmacy_request;

public class RequestDeliveryPharmacy extends AppCompatActivity {
    EditText name, area, contact;
    Button submit, ImageUploadBtn;
    TextView uploadLabel;
    Bundle extras;
    byte[] byteArray;
    InfoBeforeImageHandlerModel info;
    Bitmap bmp = null;
    DeliveryReqClass dh;
    ArrayList<String> pharmList;
    ArrayAdapter<String> myAdapter1;

    TextView textview;
    ArrayList<String> arrayList;
    Dialog dialog;
    String pharm;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PharmacyAll.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_delivery_pharmacy);

        textview = findViewById(R.id.testView);

        // initialize array list
        arrayList = new ArrayList<>();

        //To populate pharmacy list dropdown from db
        Pharmacy_request pharmacy_request = new Pharmacy_request(getApplicationContext(), "Pharmacy_request", null, 1);

        Cursor cursor = pharmacy_request.allPharList();

        if (cursor != null) {
            arrayList = new ArrayList<>();
            while (cursor.moveToNext()) arrayList.add(cursor.getString(1));
        } else
            Toast.makeText(this, "No registered pharmacies available with us at the moment", Toast.LENGTH_SHORT).show();

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog = new Dialog(RequestDeliveryPharmacy.this);

                // set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);

                // set custom height and width
                dialog.getWindow().setLayout(650, 800);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(RequestDeliveryPharmacy.this, android.R.layout.simple_list_item_1, arrayList);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        textview.setText(adapter.getItem(position));

                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });

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

        //To set TextViews and Buttons
        setFields();

        //To get and set Intent
        getAndSetIntent();

        submit = findViewById(R.id.Edit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pharm = textview.getText().toString();
                dh = new DeliveryReqClass(getApplicationContext());

                Boolean retVal = dh.setData(name.getText().toString().trim(), area.getText().toString().trim(), String.valueOf(contact.getText()).trim(), pharm.trim(), bmp, email);

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
        pharm = textview.getText().toString().trim();

        //To check and update
        info.checkAndUpdate(name.getText().toString(), area.getText().toString(), contact.getText().toString(), pharm);
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
                    textview.setText(String.valueOf(cursor.getString(4)));
                }
            }
        }
    }
}