package com.example.harshal.afinal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ticket_adapter extends BaseAdapter {

    private Context mContext;
    private List<ticket_list> mTicketlist;

    public ticket_adapter(Context mContext, List<ticket_list> mTicketlist) {
        this.mContext = mContext;
        this.mTicketlist = mTicketlist;
    }

    @Override
    public int getCount() {
        return mTicketlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mTicketlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext,R.layout.item_ticket_list,null);
        TextView tvDate =(TextView)v.findViewById(R.id.tv_date);
        TextView tvFromto=(TextView)v.findViewById(R.id.tv_fromto);
        TextView tvCost=(TextView)v.findViewById(R.id.tv_cost);
        tvDate.setText(mTicketlist.get(position).getDate());
        tvFromto.setText(mTicketlist.get(position).getFromto());
        tvCost.setText(String.valueOf(mTicketlist.get(position).getCost()) + "Rs.");

        v.setTag(mTicketlist.get(position).getId());

        return v;
    }
}
