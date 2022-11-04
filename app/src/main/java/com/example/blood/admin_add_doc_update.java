package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_add_doc_update extends AppCompatActivity {
    EditText ID, name, Email, contact, Hosp;
    Button Approve, Decline, emailBT;
    String iname, iemail, icontact, ihospital, iReq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_doc_update);

        getAndSetIntentData();

    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("ID") &&
                getIntent().hasExtra("Name") &&
                getIntent().hasExtra("Email") &&
                getIntent().hasExtra("contact") &&
                getIntent().hasExtra("Hosp") ) {
            iReq = String.valueOf(getIntent().getStringExtra("ID")).trim();
            iname = String.valueOf(getIntent().getStringExtra("Name"));
            iemail = String.valueOf(getIntent().getStringExtra("Email"));
            icontact = String.valueOf(getIntent().getStringExtra("contact"));
            ihospital = String.valueOf(getIntent().getStringExtra("Hosp"));
            Log.d("help", iReq+" "+iname+" "+iemail+" "+icontact+" "+ihospital);


            ID = findViewById(R.id.docID);
            name = findViewById(R.id.docName);
            contact = findViewById(R.id.docContact);
            Hosp = findViewById(R.id.doc_hospital);
//documents = findViewById(R.id.documents);

            ID.setText(iReq);
            name.setText(iname);
            contact.setText(icontact);
            Hosp.setText(ihospital);
            Log.d("help 2", ID+" "+name+" "+Email+" "+contact+" "+Hosp);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        emailBT = findViewById(R.id.email_btn);
        emailBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Email.class);
                intent.putExtra("email", iemail);
                intent.putExtra("subject","Request documents ");
                intent.putExtra("Body","Dear "+iname+"\n\nThank you for choosing us as your valued partner \n\n Please submit the following documents along with a CV.\n-police report \n-Images of NIC and Birth certificate \n-certifications(AL/OL/Degree) \n\nIf you have any further questions, you can reach us at +94 07x xxx xxxx , or simply reply to this email.We look forward to assisting you.\n\nRegards,\nBlueBlood team." );
                startActivity(intent);
            }
        });

        Approve = findViewById(R.id.accept_btn);
        Approve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Email.class);
                intent.putExtra("email", iemail);
                intent.putExtra("subject","Congratulations");
                intent.putExtra("Body","Dear "+iname+"\n\nThank you for choosing us as your valued partner \n\n We are happy to announce that you have been sucessfully onboarded with us. \n\nIf you have any further questions, you can reach us at +94 07x xxx xxxx , or simply reply to this email.We look forward to assisting you.\n\nRegards,\nBlueBlood team." );
                startActivity(intent);
            }
        });
        Decline = findViewById(R.id.decline_btn);
        Decline.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Email.class);
                intent.putExtra("email", iemail);
                intent.putExtra("subject","Sorry your request have been declined");
                intent.putExtra("Body","Dear "+iname+"\n\nThank you for choosing us as your valued partner \n\nUnfortunately your application got rejected. \n\nIf you have any further questions, you can reach us at +94 07x xxx xxxx , or simply reply to this email.We look forward to assisting you.\n\nRegards,\nBlueBlood team." );
                startActivity(intent);
            }
        });

    }
}