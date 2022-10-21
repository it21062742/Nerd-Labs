package com.example.blood;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterClassForRequestHistory extends RecyclerView.Adapter<AdapterClassForRequestHistory.MyViewHolder>
{
    Context mContext;
    String mReqid[], mName[],mPharmacy[],mDate[];

    //To receive the data received from the ManageRequest page
    public AdapterClassForRequestHistory(Context context, String req[], String name[], String pharm[], String date[])
    {
        mContext = context;
        mReqid = req;
        mName = name;
        mPharmacy = pharm;
        mDate = date;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_holder_request_history, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mReqs.setText(mReqid[position]);
        holder.mNames.setText(mName[position]);
        holder.mPharms.setText(mPharmacy[position]);
        holder.mDates.setText(mDate[position]);
    }

    public int getItemCount() {
        return mName.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mReqs, mNames, mDates, mPharms;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mReqs = itemView.findViewById(R.id.reqIdDataHistory);
            mNames = itemView.findViewById(R.id.nameDataHistory);
            mPharms = itemView.findViewById(R.id.pharmacyDataHistory);
            mDates = itemView.findViewById(R.id.dateDataHistory);
        }
    }
}