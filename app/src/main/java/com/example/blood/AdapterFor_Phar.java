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
    ArrayList Name1, Email1, Contact1, address1, ID1;
    Activity activity;

    AdapterFor_Phar(Activity activity,Context context, ArrayList ID,ArrayList Name, ArrayList Email, ArrayList Contact, ArrayList address) {
        this.ID1 = ID;
        this.Name1 = Name;
        this.Email1 = Email;
        this.Contact1 = Contact;
        this.address1 = address;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_pharmacy,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(ID1.get(position)));
        holder.Name.setText(String.valueOf(Name1.get(position)));
        holder.address.setText(String.valueOf(address1.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, admin_add_pharmacy_extend.class);
                intent.putExtra("ID", String.valueOf(ID1.get(holder.getAdapterPosition())));
                intent.putExtra("Email", String.valueOf(Email1.get(holder.getAdapterPosition())));
                intent.putExtra("Name", String.valueOf(Name1.get(holder.getAdapterPosition())));
                intent.putExtra("contact", String.valueOf(Contact1.get(holder.getAdapterPosition())));
                intent.putExtra("address", String.valueOf(address1.get(holder.getAdapterPosition())));

                activity.startActivityForResult(intent,1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ID1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, Name, address;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.PharId);
            Name = itemView.findViewById(R.id.PharName);
            address = itemView.findViewById(R.id.PharAddress);
            mainLayout = itemView.findViewById(R.id.mainLayoutDoc);
        }
    }
}
