package com.example.blood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.Doctor_request;
import Database.Pharmacy_request;

public class Email extends AppCompatActivity {

    Button button;
    EditText sendto, subject, body;
    Doctor_request dh;
    Pharmacy_request db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        sendto = findViewById(R.id.email_email);
        subject = findViewById(R.id.email_sub);
        body = findViewById(R.id.email_body);
        button = findViewById(R.id.btn_send);

        Intent intent1 = getIntent();
        String ID = intent1.getStringExtra("ID");
        String email = intent1.getStringExtra("email");
        String body1 = intent1.getStringExtra("Body");
        String subject1 = intent1.getStringExtra("subject");
        sendto.setText(email);
        subject.setText(subject1);
        body.setText(body1);


        button.setOnClickListener(view -> {
            String emailsend = sendto.getText().toString();
            String emailsubject = subject.getText().toString();
            String emailbody = body.getText().toString();

            // define Intent object with action attribute as ACTION_SEND
            Intent intent = new Intent(Intent.ACTION_SEND);

            // add three fields to intent using putExtra function
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailsend});
            intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
            intent.putExtra(Intent.EXTRA_TEXT, emailbody);

            // set type of intent
            intent.setType("message/rfc822");
                if (getIntent().hasExtra("id")) {
                    String id = getIntent().getStringExtra("id").toString().trim();

                    dh = new Doctor_request(getApplicationContext(), Doctor_request.TABLENAME, null, 1);
                    Cursor cursor = dh.readFromID(id);

                    if (cursor != null) {
                        cursor.moveToNext();

                        Boolean result = dh.approveDoctor(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                        if (result) {
                            boolean stats = dh.DeleteOneRow(id);

                            if(stats==true)
                                Toast.makeText(this, "Approved doctor sucessfully", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(this, "error 404.. Please try again later..", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, "error 404.. Please try again later..", Toast.LENGTH_SHORT).show();
                    }
                } else Toast.makeText(this, "Cannot Approve doctor Request", Toast.LENGTH_SHORT).show();
//Pharmacy accept
            if (getIntent().hasExtra("ID")) {
                String id = getIntent().getStringExtra("ID").toString().trim();

                db = new Pharmacy_request(getApplicationContext(), Pharmacy_request.TABLENAME, null, 1);
                Cursor cursor = db.readFromID(id);

                if (cursor != null) {
                    cursor.moveToNext();

                    Boolean result = db.approvePhar(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    if (result) {
                        boolean stats = db.DeleteOneRow(id);

                        if(stats==true)
                            Toast.makeText(this, "Approved pharmacy sucessfully", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(this, "error 404.. Please try again later..", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "error 404.. Please try again later..", Toast.LENGTH_SHORT).show();
                }
            } else Toast.makeText(this, "Cannot Approve pharmacy Request", Toast.LENGTH_SHORT).show();



            // startActivity with intent with chooser as Email client using createChooser function
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });
    }
}