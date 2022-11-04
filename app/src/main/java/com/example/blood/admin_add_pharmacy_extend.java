package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

            ID.setText(iReq);
            name.setText(iname);
            address.setText(iaddress);
            Log.d("help 2", ID+" "+name+" "+Email+" "+contact+" "+address);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        Approve = findViewById(R.id.approve_btn);
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

        Decline = findViewById(R.id.decline_btn2);
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

        emailBT = findViewById(R.id.Phar_email_btn);
        emailBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Email.class);
                intent.putExtra("email", iemail);
                intent.putExtra("subject","Request documents ");
                intent.putExtra("Body","Dear "+iname+"\n\nThank you for choosing us as your valued partner \n\n Please submit the following documents along with a CV.\n-police report \n-Images of NIC and Birth certificate \n-certifications(TAX/Owwnership)\nAnd other Documents. \n\nIf you have any further questions, you can reach us at +94 07x xxx xxxx , or simply reply to this email.We look forward to assisting you.\n\nRegards,\nBlueBlood team." );
                startActivity(intent);
            }
        });
    }
}