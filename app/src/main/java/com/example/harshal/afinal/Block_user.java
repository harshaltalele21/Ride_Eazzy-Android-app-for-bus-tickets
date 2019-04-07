package com.example.harshal.afinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Block_user extends AppCompatActivity {

    Button submit;
    EditText aadhaar,user;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference cust = root.child("Customer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_user);

        submit = findViewById(R.id.submit);
        aadhaar = findViewById(R.id.user);
        user = findViewById(R.id.uid);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String uid = user.getText().toString();
                final String ad = aadhaar.getText().toString();

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){
                            int flag = 0;
                            for(DataSnapshot ds : dataSnapshot.getChildren()){
                                if(ds.child("aadhaar_no").getValue().toString().equals(ad) && ds.child("user_id").getValue().toString().equals(uid)){
                                    flag =1;
                                    DatabaseReference del = ds.getRef();
                                    del.removeValue();
                                    Toast.makeText(Block_user.this, "User Blocked!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Block_user.this, Admin_Dashboard.class);
                                    startActivity(i);
                                }
                                if(flag != 1){
                                    Toast.makeText(Block_user.this, "User Not Found!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                cust.addListenerForSingleValueEvent(eventListener);
            }
        });
    }
}
