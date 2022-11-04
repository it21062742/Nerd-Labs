package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button button =findViewById(R.id.add_doc_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),admin_add_doc_brief.class);
                startActivity(i);
            }
        });
        //when pressed on the button it will open the pharmacy brief page
        Button button2 = findViewById(R.id.add_pharmacy_btn);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),admin_add_pharmacy_brief.class);
                startActivity(a);
            }
        });

        Button button3 = findViewById(R.id.all_pharmacy_btn);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),Phar_all.class);
                startActivity(a);
            }
        });

        Button button4 = findViewById(R.id.all_doc_btn);
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),Doc_all.class);
                startActivity(a);
            }
        });
    }
    //disable back button so that user cant go into login page

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
    }
}
