package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import Database.Pharmacy_request;

public class user_self_add_pharmacy extends AppCompatActivity {
    private EditText Name, address, Contact, email, documents;
    private Button submit_btn;
    private CheckBox terms;
    private Pharmacy_request PharmacyHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_self_add_pharmacy);

        Button button =findViewById(R.id.doc_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                overridePendingTransition(0, 0 );
                Intent i = new Intent(getApplicationContext(),user_self_add_doc.class);
                startActivity(i);
            }
        });
        //database contention start
//initialize variables
        Name = findViewById(R.id.phar_name);
        email = findViewById(R.id.phar_email);
        address = findViewById(R.id.phar_address);
        Contact = findViewById(R.id.phar_contact);
        terms = findViewById(R.id.pricy_polcy_chkbox);
        submit_btn = findViewById(R.id.Submit_btn_phar);

        PharmacyHandler = new Pharmacy_request(user_self_add_pharmacy.this, Pharmacy_request.TABLENAME, null, 1);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is to get data from all edit text fields.
                String Name1 = Name.getText().toString();
                String Email1 = email.getText().toString();
                String Contact1 = Contact.getText().toString();
                String address1 = address.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(new Date());
                // validating if the text fields are empty or not.
                if (Name1.isEmpty() || Email1.isEmpty() || Contact1.isEmpty() || address1.isEmpty()) {
                    Toast.makeText(user_self_add_pharmacy.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                PharmacyHandler.AddNewEntry(Name1, Email1, Contact1, address1, date1);
                // after adding the data we are displaying a toast message.
                Toast.makeText(user_self_add_pharmacy.this, "Request has been added.", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),HomePage.class);
        startActivity(i);
    }
}