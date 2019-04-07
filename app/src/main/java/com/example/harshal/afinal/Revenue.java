package com.example.harshal.afinal;

import android.provider.ContactsContract;
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

public class Revenue extends AppCompatActivity {

    private ListView lvRevenue;
    private RevListAdapter adaptor;
    private List<RevList> mRevtlist;
    final String[] name = new String[10];
    final String[] date = new String[10];
    final int[] amount = new int[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        lvRevenue= (ListView)findViewById(R.id.listview_revenue);

        mRevtlist=new ArrayList<>();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myref = root.child("Revenue");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    int i=0;
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        String nm = ds.child("name").getValue().toString().trim();
                        //Toast.makeText(Revenue.this, name, Toast.LENGTH_SHORT).show();
                        String dt = ds.child("date").getValue().toString().trim();
                        //Toast.makeText(Revenue.this, date, Toast.LENGTH_SHORT).show();
                        int amt = Integer.parseInt(ds.child("amount").getValue().toString());
                        name[i]=nm;
                        //Toast.makeText(Revenue.this, name[i], Toast.LENGTH_SHORT).show();
                        date[i]=dt;
                        amount[i]=amt;
                        i++;
                    }

                }
                for(int k=0;k<10;k++){

                    mRevtlist.add(new RevList(k+1,date[k],name[k],amount[k]));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        myref.addListenerForSingleValueEvent(eventListener);

        //Toast.makeText(this, name[1], Toast.LENGTH_LONG).show();



        mRevtlist.add(new RevList(1,"12-04-2019","Harshal Talele",200));
        mRevtlist.add(new RevList(2,"12-02-2019","Aman Singh",200));
        mRevtlist.add(new RevList(3,"13-04-2019","Ankita Tupe",500));
        mRevtlist.add(new RevList(4,"02-04-2019","Ritesh Shirude",2000));
        mRevtlist.add(new RevList(5,"28-05-2019","Sumedh Joshi",200));
        mRevtlist.add(new RevList(6,"11-06-2019","Rishi Mahajan",2000));
        mRevtlist.add(new RevList(7,"24-07-2019","Pranav Badhe",2000));
        mRevtlist.add(new RevList(7,"24-07-2019","Anish Mahajan",200));

        adaptor=new RevListAdapter(getApplicationContext(),mRevtlist);
        lvRevenue.setAdapter(adaptor);

        lvRevenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked ticket ID ="+view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
