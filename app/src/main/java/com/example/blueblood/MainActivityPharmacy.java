package com.example.blueblood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pharmacy.R;

public class MainActivityPharmacy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pharmacy);

        //For back Button (Also set the parent activity in AdnroidManifest.xml)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}