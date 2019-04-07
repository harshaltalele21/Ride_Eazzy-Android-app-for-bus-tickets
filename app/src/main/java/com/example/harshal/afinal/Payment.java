package com.example.harshal.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment extends AppCompatActivity {

    EditText date,amount;
    Button py;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = root.child("Revenue");
    int fin_amt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        date = findViewById(R.id.date);
        amount = findViewById(R.id.amount);
        py = findViewById(R.id.pay);

        Random r = new Random();
        date.setEnabled(false);

        final DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date today = new Date();
        date.setText("11-11-2018");
        int n = r.nextInt(5000);
        //amount.setText(String.valueOf(n));
        //amount.setEnabled(false);
        //int amt = 0;

        SharedPreferences sharedp = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        final String usid = sharedp.getString("user_id","");


        DatabaseReference ref2 = root.child("Ticket_History").child(usid);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    int amt =0;
                    for(DataSnapshot ds : dataSnapshot.getChildren()){

                        String a = ds.child("amount").getValue().toString();
                        int i = Integer.parseInt(a);
                        amt = amt+i;
                    }
                    //amount.setText(String.valueOf(amt));
                    fin_amt = amt;
                    //amount.setEnabled(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref2.addListenerForSingleValueEvent(eventListener);

        ValueEventListener eventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    int amt1 =0;
                    for(DataSnapshot ds : dataSnapshot.getChildren()){

                        if(ds.child("user_id").getValue().toString().equals(usid)){
                            String a = ds.child("amount").getValue().toString();
                            int i = Integer.parseInt(a);
                            amt1 = amt1+i;
                        }
                    }
                    //amount.setText(String.valueOf(amt));
                    fin_amt = fin_amt-amt1;
                    amount.setText(String.valueOf(fin_amt));
                    amount.setEnabled(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener1);

        py.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dt = date.getText().toString();
                String amt = amount.getText().toString();

                ref.child(d.format(today)).child("name").setValue(usid);
                ref.child(d.format(today)).child("date").setValue(d.format(today));
                ref.child(d.format(today)).child("amount").setValue(amt);
                ref.child(d.format(today)).child("user_id").setValue(usid);

                amount.setText("0");
            }
        });

    }
}
