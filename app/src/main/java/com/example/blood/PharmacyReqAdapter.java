package com.example.blood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PharmacyReqAdapter extends RecyclerView.Adapter<PharmacyReqAdapter.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList reqID, date, name, pharm, area, cont;
    Animation transition;


    PharmacyReqAdapter(Activity activity, Context context, ArrayList reqID, ArrayList date, ArrayList name, ArrayList pharm, ArrayList cont, ArrayList area)
    {
        this.context = context;
        this.reqID = reqID;
        this.date = date;
        this.name = name;
        this.pharm = pharm;
        this.area = area;
        this.cont = cont;
        this.activity = activity;
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
                intent.putExtra("area", String.valueOf(area.get(holder.getAdapterPosition())));
                intent.putExtra("cont", String.valueOf(cont.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reqID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, patName, pharmName, bkDate, area;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.reqID);
            patName = itemView.findViewById(R.id.DocName);
            pharmName = itemView.findViewById(R.id.pharmName);
            bkDate = itemView.findViewById(R.id.date);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //For animation
            transition = AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim);
            mainLayout.setAnimation(transition);
        }
    }
}
