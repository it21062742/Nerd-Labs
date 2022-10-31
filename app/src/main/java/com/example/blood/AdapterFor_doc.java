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

public class AdapterFor_doc extends RecyclerView.Adapter<AdapterFor_doc.MyViewHolder>{

    Context context;
    ArrayList Name, Email, Contact, Hospital, Date, ID;
    AdapterFor_doc AdapterFor_doc;
    Activity activity;
    Animation transition;
    RecyclerView recyclerview;

    AdapterFor_doc(Activity activity,Context context, ArrayList ID,ArrayList Name, ArrayList Email, ArrayList Contact, ArrayList Hospital, ArrayList Date) {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Contact = Contact;
        this.Hospital = Hospital;
        this.Date = Date;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterFor_doc.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_doc_pharmacy,parent, false);
        return new AdapterFor_doc.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFor_doc.MyViewHolder holder, final int position) {
        holder.id.setText(String.valueOf(ID.get(position)));
        holder.Name.setText(String.valueOf(Name.get(position)));
        holder.Date.setText(String.valueOf(Date.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, admin_add_doc_extend.class);
                intent.putExtra("ID", String.valueOf(ID.get(holder.getAdapterPosition())));
                intent.putExtra("name", String.valueOf(Name.get(holder.getAdapterPosition())));
                intent.putExtra("Email", String.valueOf(Email.get(holder.getAdapterPosition())));
                intent.putExtra("date", String.valueOf(Date.get(holder.getAdapterPosition())));
                intent.putExtra("contact", String.valueOf(Contact.get(holder.getAdapterPosition())));
                intent.putExtra("Hosp", String.valueOf(Hospital.get(holder.getAdapterPosition())));

                activity.startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, Name, Date;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.ID);
            Name = itemView.findViewById(R.id.Name);
            Date = itemView.findViewById(R.id.Date);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //For animation
            transition = AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim);
            mainLayout.setAnimation(transition);
        }
    }
}
