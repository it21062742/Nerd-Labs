package com.example.blood;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_forAllPhar extends RecyclerView.Adapter<adapter_forAllPhar.MyViewHolder>{

    Context context;
    ArrayList Name1, Email1, Contact1, address1, ID1;
    Activity activity;

    adapter_forAllPhar(Activity activity,Context context, ArrayList ID,ArrayList Name, ArrayList Email, ArrayList Contact, ArrayList address) {
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
    public adapter_forAllPhar.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_pharmacy,parent, false);
        return new adapter_forAllPhar.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_forAllPhar.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(ID1.get(position)));
        holder.Name.setText(String.valueOf(Name1.get(position)));
        holder.address.setText(String.valueOf(address1.get(position)));

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