package com.example.blood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFor_Phar extends RecyclerView.Adapter<AdapterFor_Phar.MyViewHolder>{

    Context context;
    ArrayList Name, Email, Contact, Hospital, ID;
    Activity activity;

    AdapterFor_Phar(Activity activity,Context context, ArrayList ID,ArrayList Name, ArrayList Email, ArrayList Contact, ArrayList Hospital) {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Contact = Contact;
        this.Hospital = Hospital;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterFor_Phar.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_doc_pharmacy,parent, false);
        return new AdapterFor_Phar.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(ID.get(position)));
        holder.Name.setText(String.valueOf(Name.get(position)));
        holder.Email.setText(String.valueOf(Email.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, admin_add_doc_update.class);
                intent.putExtra("ID", String.valueOf(ID.get(position)));
                intent.putExtra("Name", String.valueOf(Name.get(position)));
                intent.putExtra("Email", String.valueOf(Email.get(position)));
                intent.putExtra("Contact", String.valueOf(Contact.get(position)));
                intent.putExtra("Hosp", String.valueOf(Hospital.get(position)));

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, Name, Email;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.ID);
            Name = itemView.findViewById(R.id.Name);
            Email = itemView.findViewById(R.id.Email);
            mainLayout = itemView.findViewById(R.id.mainLayoutDoc);
        }
    }
}
