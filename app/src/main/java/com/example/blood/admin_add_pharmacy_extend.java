package com.example.blood;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_add_pharmacy_extend extends AppCompatActivity {
    EditText ID, name, Email, contact, address;
    Button Approve, Decline, emailBT;
    String iname, iemail, icontact, iaddress, iReq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_pharmacy_extend);

        getAndSetIntentData();

    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("ID") &&
                getIntent().hasExtra("Name") &&
                getIntent().hasExtra("Email") &&
                getIntent().hasExtra("contact") &&
                getIntent().hasExtra("address") ) {
            iReq = String.valueOf(getIntent().getStringExtra("ID")).trim();
            iname = String.valueOf(getIntent().getStringExtra("Name"));
            iemail = String.valueOf(getIntent().getStringExtra("Email"));
            icontact = String.valueOf(getIntent().getStringExtra("contact"));
            iaddress = String.valueOf(getIntent().getStringExtra("address"));
            Log.d("help", iReq+" "+iname+" "+iemail+" "+icontact+" "+iaddress);


            ID = findViewById(R.id.pid);
            name = findViewById(R.id.Pname);
            address = findViewById(R.id.Paddress);
//documents = findViewById(R.id.documents);
            Approve = findViewById(R.id.approve_btn);
            Decline = findViewById(R.id.decline_btn2);
            emailBT = findViewById(R.id.Phar_email_btn);


            ID.setText(iReq);
            name.setText(iname);
            address.setText(iaddress);
            Log.d("help 2", ID+" "+name+" "+Email+" "+contact+" "+address);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}