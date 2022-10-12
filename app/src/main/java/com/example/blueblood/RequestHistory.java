package com.example.blueblood;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RequestHistory extends AppCompatActivity {

    RecyclerView reqHistoryRv;
    TextView req, name,pharmac, dates;
    String strReqId[], strDate[], strPharmacy[], strName[];
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_history);

        //For back Button (Also set the parent activity in AdnroidManifest.xml)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reqHistoryRv = findViewById(R.id.RequestHistoryRV);
        strReqId = getResources().getStringArray(R.array.reqId);
        strDate = getResources().getStringArray(R.array.Date);
        strPharmacy = getResources().getStringArray(R.array.pharmacyList);
        strName = getResources().getStringArray(R.array.name);


        //To call the adapter and pass data
        AdapterClassForRequestHistory mAdapter = new AdapterClassForRequestHistory
                (this, strReqId, strName, strPharmacy,strDate);

        reqHistoryRv.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        reqHistoryRv.setLayoutManager(mLayoutManager);

//        setHasFixedSize can be set to true if we exactly know the number of items
//        reqHistoryRv.setHasFixedSize(true);
    }
}