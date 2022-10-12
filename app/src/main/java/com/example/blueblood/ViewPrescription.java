package com.example.blueblood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ViewPrescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescription);

        //For back Button (Also set the parent activity in AdnroidManifest.xml)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}