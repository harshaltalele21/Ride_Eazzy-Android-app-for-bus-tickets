package com.example.harshal.afinal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Complaint extends AppCompatActivity {

    private ListView lvcomplaint;
    private Complaint_adapter adapter;
    private List<Complaint_list> mComplaintlist;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference cmp = root.child("Problems");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        lvcomplaint = (ListView) findViewById(R.id.listview_complaint);

        mComplaintlist = new ArrayList<>();

        /*ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        String rt = ds.child("route").getValue().toString();
                        String pr = ds.child("problem").getValue().toString();
                        //Toast.makeText(Complaint.this, pr, Toast.LENGTH_SHORT).show();
//                        mComplaintlist.add(new Complaint_list(1,"12-78-7777",rt,pr));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        cmp.addListenerForSingleValueEvent(eventListener);*/



        mComplaintlist.add(new Complaint_list(1, "12-04-2018", "102","Flat Tire"));
        mComplaintlist.add(new Complaint_list(2, "12-08-2018", "103","Pending payment of employees"));
        mComplaintlist.add(new Complaint_list(3, "10-10-2018", "201","Window broken"));
        mComplaintlist.add(new Complaint_list(4, "01-04-2018", "102","No issue just saying HI bro"));
        mComplaintlist.add(new Complaint_list(5, "08-04-2019", "1769","Lord Voldemort"));
        mComplaintlist.add(new Complaint_list(6, "12-04-2019", "420","Driver lost"));



        adapter=new Complaint_adapter(getApplicationContext(),mComplaintlist);
        lvcomplaint.setAdapter(adapter);

        lvcomplaint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked complaint ="+view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}