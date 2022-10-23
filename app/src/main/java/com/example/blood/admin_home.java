package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        Button button2 = findViewById(R.id.add_pharmacy_btn);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),admin_add_pharmacy_brief.class);
                startActivity(a);
            }
        });
    }
}
