package com.example.blood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PharmacyCompletedAdapter extends RecyclerView.Adapter<PharmacyCompletedAdapter.MyViewHolder> {

    Context context;
    ArrayList reqID, date, name, pharm, area, cont;

    PharmacyCompletedAdapter(Context context, ArrayList reqID, ArrayList date, ArrayList name, ArrayList pharm, ArrayList cont, ArrayList area)
    {
        this.context = context;
        this.reqID = reqID;
        this.date = date;
        this.name = name;
        this.pharm = pharm;
        this.area = area;
        this.cont = cont;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pharm_comp_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(reqID.get(position)));
        holder.patName.setText(String.valueOf(name.get(position)));
        holder.pharmName.setText(String.valueOf(pharm.get(position)));
        holder.bkDate.setText(String.valueOf(date.get(position)));
    }

    @Override
    public int getItemCount() {
        return reqID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, patName, pharmName, bkDate, area;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.compReqID);
            patName = itemView.findViewById(R.id.compPatientName);
            pharmName = itemView.findViewById(R.id.compPharm);
            bkDate = itemView.findViewById(R.id.compDate);
        }
    }
}
