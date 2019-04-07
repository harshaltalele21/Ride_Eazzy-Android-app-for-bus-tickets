package com.example.harshal.afinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Complaint_adapter extends BaseAdapter {

    private Context mContext;
    private List<Complaint_list> mComplist;

    public Complaint_adapter(Context mContext, List<Complaint_list> mComplist) {
        this.mContext = mContext;
        this.mComplist = mComplist;
    }

    @Override
    public int getCount() {
        return mComplist.size();
    }

    @Override
    public Object getItem(int position) {
        return mComplist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext,R.layout.activity_complaint_adapter,null);
        TextView tvDate =(TextView)v.findViewById(R.id.tv_date);
        TextView tvFrom=(TextView)v.findViewById(R.id.tv_name);
        TextView tvComp=(TextView)v.findViewById(R.id.tv_complaint);
        tvDate.setText(mComplist.get(position).getDate());
        tvFrom.setText(mComplist.get(position).getName());
        tvComp.setText(String.valueOf(mComplist.get(position).getComplaint()) );

        v.setTag(mComplist.get(position).getId());

        return v;
    }
}
