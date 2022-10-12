package com.example.blueblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterClassForManageRequest extends RecyclerView.Adapter<AdapterClassForManageRequest.MyViewHolder>
{
    Context mContext;
    String mReqid[], mName[],mPharmacy[],mDate[];

    //To receive the data received from the ManageRequest page
    public AdapterClassForManageRequest(Context context, String req[], String name[], String pharm[], String date[])
    {
        mContext = context;
        mReqid = req;
        mName = name;
        mPharmacy = pharm;
        mDate = date;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_holder_manage_request, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mReqs.setText(mReqid[position]);
        holder.mNames.setText(mName[position]);
        holder.mPharms.setText(mPharmacy[position]);
        holder.mDates.setText(mDate[position]);
    }

    @Override
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
