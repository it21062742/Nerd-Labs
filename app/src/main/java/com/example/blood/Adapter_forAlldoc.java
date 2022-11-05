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


    public class Adapter_forAlldoc extends RecyclerView.Adapter<com.example.blood.Adapter_forAlldoc.MyViewHolder>{

        Context context;
        ArrayList Name, Email, Contact, Hospital, ID;
        Activity activity;

        Adapter_forAlldoc(Activity activity,Context context, ArrayList ID,ArrayList Name, ArrayList Email, ArrayList Contact, ArrayList Hospital) {
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
        public com.example.blood.Adapter_forAlldoc.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.all_info,parent, false);
            return new com.example.blood.Adapter_forAlldoc.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

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

                id = itemView.findViewById(R.id.ID1);
                Name = itemView.findViewById(R.id.Name1);
                Email = itemView.findViewById(R.id.Email1);
                mainLayout = itemView.findViewById(R.id.mainLayoutAll);
            }
        }
    }

