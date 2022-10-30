package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DoctorHandler;

public class user_self_add_doc extends AppCompatActivity {
    private EditText Name, Email, Contact, Hospital, qualifications;
    private Button submit_btn;
    private DoctorHandler DoctorHandler;
    private CheckBox terms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_self_add_doc);
//switch between docter or pharmacy page
        Button button =findViewById(R.id.pharmacy_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),user_self_add_pharmacy.class);
                startActivity(i);
            }
        });
//database contention start
//initialize variables
        terms = findViewById(R.id.checkBox);
        Name = findViewById(R.id.editTextTextPersonName);
        Email = findViewById(R.id.TextEmail);
        Contact = findViewById(R.id.editTextPhone);
        Hospital = findViewById(R.id.HospitalName);
        submit_btn = findViewById(R.id.submit_btn);


            DoctorHandler = new DoctorHandler(user_self_add_doc.this);
            submit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // below line is to get data from all edit text fields.
                    String Name1 = Name.getText().toString();
                    String Email1 = Email.getText().toString();
                    String Contact1 = Contact.getText().toString();
                    String Hospital1 = Hospital.getText().toString();

                        // validating if the text fields are empty or not.
                        if (Name1.isEmpty() || Email1.isEmpty() || Contact1.isEmpty() || Hospital1.isEmpty()) {
                            Toast.makeText(user_self_add_doc.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                        DoctorHandler.AddNewEntry(Name1, Email1, Contact1, Hospital1);
                        // after adding the data we are displaying a toast message.
                        Toast.makeText(user_self_add_doc.this, "Request has been added.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),HomePage.class);
        startActivity(i);
    }
}