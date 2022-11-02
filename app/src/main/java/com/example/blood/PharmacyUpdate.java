package com.example.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.blood.UtilAndModel.DeliveryReqClass;

import java.io.ByteArrayInputStream;

import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class PharmacyUpdate extends AppCompatActivity {

    EditText name, area, cont;
    Button edit, confirm;
    String intName, intCont, intArea;
    String intReq;
    Boolean clicked;
    ImageView pharmImage;
    DeliveryReqClass dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_update);

        dr = new DeliveryReqClass(getApplicationContext());

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
        pharmImage = findViewById(R.id.prescPhoto);

        getAndSetIntentData();

        //To set the image.
        Cursor imageCursor = dr.getImageOnId(intReq);

        imageCursor.moveToNext();

        if (imageCursor.getCount() > 0 && imageCursor != null) {
            byte[] imageBytes = imageCursor.getBlob(7);

            byte[] img = imageCursor.getBlob(7);
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            pharmImage.setImageBitmap(bitmap);
        }

        name.setEnabled(false);
        area.setEnabled(false);
        cont.setEnabled(false);
        clicked = false;

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true; // whn edit button pressed. //Now confirm button is save button

                name.setEnabled(true);
                area.setEnabled(true);
                cont.setEnabled(true);

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

                                Boolean updateStatus = dr.UpdateOnID(name.getText().toString(), area.getText().toString(),
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

                    dr.updateStatusToComplete(intReq);

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
            intReq = String.valueOf(getIntent().getStringExtra("reqID")).trim();
            intName = String.valueOf(getIntent().getStringExtra("name"));
            intCont = String.valueOf(getIntent().getStringExtra("cont"));
            intArea = String.valueOf(getIntent().getStringExtra("area"));

            name.setText(intName.toString());
            area.setText(intArea.toString());
            cont.setText(intCont.toString());
        }
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
        builder.setTitle("Delete Request");
        builder.setMessage("Ongoing Request Will Be Cancelled");
        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getAndSetIntentData();

                boolean deleteStatus = dr.DeleteOneRow(intReq);

                if (deleteStatus == true)
                    Toast.makeText(getApplicationContext(), "Request Deleted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "error: 404.. Please Try again later", Toast.LENGTH_SHORT).show();

                Intent ii = new Intent(getApplicationContext(), PharmacyAll.class);
                startActivity(ii);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}