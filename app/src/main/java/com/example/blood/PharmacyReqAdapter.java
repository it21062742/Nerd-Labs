package com.example.blood;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PharmacyReqAdapter extends RecyclerView.Adapter<PharmacyReqAdapter.MyViewHolder>{

    Context context;

    ArrayList reqID, date, name, pharm;


    PharmacyReqAdapter(Context context, ArrayList reqID, ArrayList date, ArrayList name, ArrayList pharm)
    {
        this.context = context;
        this.reqID = reqID;
        this.date = date;
        this.name = name;
        this.pharm = pharm;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.pharmacy_req_row,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.id.setText(String.valueOf(reqID.get(position)));
        holder.patName.setText(String.valueOf(name.get(position)));
        holder.pharmName.setText(String.valueOf(pharm.get(position)));
        holder.bkDate.setText(String.valueOf(date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PharmacyUpdate.class);
                intent.putExtra("reqID", String.valueOf(reqID.get(holder.getAdapterPosition())));
                intent.putExtra("name", String.valueOf(name.get(holder.getAdapterPosition())));
                intent.putExtra("pharm", String.valueOf(pharm.get(holder.getAdapterPosition())));
                intent.putExtra("date", String.valueOf(date.get(holder.getAdapterPosition())));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reqID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, patName, pharmName, bkDate;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.reqID);
            patName = itemView.findViewById(R.id.patientName);
            pharmName = itemView.findViewById(R.id.pharmacyName);
            bkDate = itemView.findViewById(R.id.Date);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
