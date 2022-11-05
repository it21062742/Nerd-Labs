package com.example.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blood.UtilAndModel.DeliveryReqClass;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;
import Database.Pharmacy_request;

public class PharmacyUpdate extends AppCompatActivity {

    EditText name, area, cont;
    Button edit, confirm;
    String intName, intCont, intArea, intPharm;
    String intReq;
    Boolean clicked;
    ImageView pharmImage;
    DeliveryReqClass dr;
    String pharm;
    Dialog dialog;

    TextView textview;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_update);

        textview = findViewById(R.id.testView);

        // initialize array list
        arrayList = new ArrayList<>();

        //To populate pharmacy list dropdown from db
        Pharmacy_request pharmacy_request = new Pharmacy_request(getApplicationContext(), "Pharmacy_request", null, 1);

        Cursor cursor = pharmacy_request.allPharList();

        arrayList = new ArrayList<>();
        while (cursor.moveToNext()) arrayList.add(cursor.getString(1));

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog = new Dialog(PharmacyUpdate.this);

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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

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

        //ArrayAdapter is the container that willl hold the values and then integrate them with the spinner
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(PharmacyUpdate.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pharmacyList));


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
        textview.setEnabled(false);
        clicked = false;

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true; // whn edit button pressed. //Now confirm button is save button

                name.setEnabled(true);
                area.setEnabled(true);
                cont.setEnabled(true);
                textview.setEnabled(true);

                confirm.setText("SAVE CHANGES");
                edit.setVisibility(View.GONE);

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        pharm = textview.getText().toString();
                        Boolean updateStatus = dr.UpdateOnID(name.getText().toString(), area.getText().toString(),
                                cont.getText().toString(), pharm, intReq);

                        if (updateStatus == true) {
                            clicked = false;
                            Intent i1 = new Intent(getApplicationContext(), PharmacyAll.class);
                            startActivity(i1);
                        }
                    }
                });
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked == false) {
                    confirmReqDelivery();
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
            intPharm = String.valueOf(getIntent().getStringExtra("pharm"));

            name.setText(intName.toString());
            area.setText(intArea.toString());
            cont.setText(intCont.toString());
            textview.setText(intPharm.toString());
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

    void confirmReqDelivery() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delivery Confirmation");
        builder.setMessage("Confirm Delivery Received");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Delivery Confirmed. Thank You For Using Our Services", Toast.LENGTH_LONG).show();

                confirmReqDelivery();
                dr.updateStatusToComplete(intReq);

                Intent i1 = new Intent(getApplicationContext(), PharmacyAll.class);
                startActivity(i1);
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