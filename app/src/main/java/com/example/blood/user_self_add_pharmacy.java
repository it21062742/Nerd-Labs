package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_self_add_pharmacy extends AppCompatActivity {

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

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),HomePage.class);
        startActivity(i);
    }
}