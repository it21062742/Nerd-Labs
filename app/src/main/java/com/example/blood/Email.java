package com.example.blood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import Database.Doctor_request;

import androidx.appcompat.app.AppCompatActivity;

public class Email extends AppCompatActivity {

    Button button;
    EditText sendto, subject, body;
    Doctor_request dh;

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

            if(getIntent().hasExtra("id"))
            {
                String id = getIntent().getStringExtra("id").toString().trim();

                dh = new Doctor_request(getApplicationContext(), Doctor_request.TABLENAME, null, 1);
                Cursor cursor = dh.readFromID(id);


            }

            // startActivity with intent with chooser as Email client using createChooser function
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });
    }
}