package com.example.blood;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ManageRequest extends AppCompatActivity {

    RecyclerView manageRequestRv;
    TextView req, name,pharmac, dates;
    String strReqId[], strDate[], strPharmacy[], strName[];
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //For back Button (Also set the parent activity in AdnroidManifest.xml)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_request);

        manageRequestRv = findViewById(R.id.RequestHistoryRV);
        strReqId = getResources().getStringArray(R.array.reqId);
        strDate = getResources().getStringArray(R.array.Date);
        strPharmacy = getResources().getStringArray(R.array.pharmacyList);
        strName = getResources().getStringArray(R.array.name);


        //To call the adapter and pass data
        AdapterClassForManageRequest mAdapter = new AdapterClassForManageRequest
                (this, strReqId, strName, strPharmacy,strDate);

        manageRequestRv.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        manageRequestRv.setLayoutManager(mLayoutManager);

//        setHasFixedSize can be set to true if we exactly know the number of items
//        manageRequestRv.setHasFixedSize(true);
    }
}