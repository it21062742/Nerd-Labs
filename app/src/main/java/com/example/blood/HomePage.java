package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Database.CurrentReqHandler;
import Database.CurrentUser;
import Database.DeliveryReqHandler;
import Database.DeliveryReqTable;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button button1 = findViewById(R.id.channelingBtn);
        Button button2 = findViewById(R.id.joinBtn);
        Button button3 = findViewById(R.id.pharmacyBtn);
        Button button4 = findViewById(R.id.HomeCareBtn);


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), error_404.class);
                startActivity(i);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), user_self_add_doc.class);
                startActivity(i);
            }
        });

        CurrentReqHandler dh = new CurrentReqHandler(this, CurrentUser.PresentUser.TABLENAME, null, 1);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PharmacyAll.class);
                String email = String.valueOf(getIntent().getStringExtra("email"));
                i.putExtra("email", email);

                Log.d("email", email);

                //Setting the Active User in the CurrentUserTable
                Cursor cursor = dh.getUser();

                if (cursor.getCount() > 0 && email != null)
                    dh.updateUser(email);
                else
                    dh.addUser(email);

                startActivity(i);
                Toast.makeText(HomePage.this, "Ongoing Request Page", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), error_404.class);
                startActivity(i);
            }
        });
    }

    //disable back button so that user cant go into login page
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
    }
}