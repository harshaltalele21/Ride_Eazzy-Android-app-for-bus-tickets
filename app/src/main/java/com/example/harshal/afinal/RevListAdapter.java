package com.example.harshal.afinal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RevListAdapter extends BaseAdapter {

    private Context mContext;
    private List<RevList> mRevlist;

    public RevListAdapter(Context mContext, List<RevList> mRevlist) {
        this.mContext = mContext;
        this.mRevlist = mRevlist;
    }

    @Override
    public int getCount() {
        return mRevlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mRevlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext,R.layout.rev_list,null);
        TextView tvDate =(TextView)v.findViewById(R.id.tv_date);
        TextView tvFromto=(TextView)v.findViewById(R.id.tv_name);
        TextView tvCost=(TextView)v.findViewById(R.id.tv_cost);
        tvDate.setText(mRevlist.get(position).getDate());
        tvFromto.setText(mRevlist.get(position).getName());
        tvCost.setText(String.valueOf(mRevlist.get(position).getCost()) + "Rs.");

        v.setTag(mRevlist.get(position).getId());

        return v;
    }
}
