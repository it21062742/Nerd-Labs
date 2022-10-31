package com.example.blood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFor_Phar extends RecyclerView.Adapter<AdapterFor_Phar.MyViewHolder>{

    Context context;
    ArrayList Name, Email, Contact, Hospital, Date, ID;
    AdapterFor_Phar AdapterFor_Phar;
    RecyclerView recyclerview;

    AdapterFor_Phar(Context context, ArrayList ID,ArrayList Name, ArrayList Email, ArrayList Contact, ArrayList Hospital, ArrayList Date) {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Contact = Contact;
        this.Hospital = Hospital;
        this.Date = Date;

    }


    @NonNull
    @Override
    public com.example.blood.AdapterFor_Phar.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_doc_pharmacy, parent, false);
        return new AdapterFor_Phar.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.blood.AdapterFor_Phar.MyViewHolder holder, int position) {
        holder.Doc_id.setText(String.valueOf(ID.get(position)));
        holder.Doc_Name.setText(String.valueOf(Name.get(position)));
        holder.Doc_Date.setText(String.valueOf(Date.get(position)));
    }

    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Doc_id, Doc_Name, Doc_Date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Doc_id = (TextView) itemView.findViewById(R.id.ID);
            Doc_Name = (TextView) itemView.findViewById(R.id.Name);
            Doc_Date = (TextView) itemView.findViewById(R.id.Date);

        }

    }
}
