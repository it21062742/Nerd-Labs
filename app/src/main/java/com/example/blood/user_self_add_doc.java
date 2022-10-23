package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_self_add_doc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_self_add_doc);

        Button button =findViewById(R.id.pharmacy_btn);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),user_self_add_pharmacy.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),HomePage.class);
        startActivity(i);
    }
}