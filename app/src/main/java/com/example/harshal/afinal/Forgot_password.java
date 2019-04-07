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

public class Forgot_password extends AppCompatActivity {

    EditText user,mob,ad,password;
    Button otp;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = root.child("Customer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        user = findViewById(R.id.user);
        mob = findViewById(R.id.mob_no);
        otp = findViewById(R.id.get_otp);
        ad = findViewById(R.id.aahdaar);
        password = findViewById(R.id.pass);

        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            int flag = 0;

                            String ad_entered = ad.getText().toString().trim();
                            String mob_entered = mob.getText().toString().trim();
                            String pass_entered = password.getText().toString().trim();
                            String usid_entered = user.getText().toString().trim();

                            for(DataSnapshot ds : dataSnapshot.getChildren()){

                                String adn = ds.child("aadhaar_no").getValue().toString();
                                String mob = ds.child("mobile_no").getValue().toString();
                                String usid = ds.child("user_id").getValue().toString();
                                if(ad_entered.equals(adn) && mob_entered.equals(mob) && usid_entered.equals(usid)) {

                                    ds.child("password").getRef().setValue(pass_entered);
                                    flag = 1;

                                    Toast.makeText(Forgot_password.this, "Password Changed!", Toast.LENGTH_SHORT).show();
                                    Intent back = new Intent(Forgot_password.this,Login_page.class);
                                    startActivity(back);
                                    break;

                                }
                                else{
                                    flag =0;
                                }
                            }
                            if(flag==0){
                                Toast.makeText(Forgot_password.this, "Enter a valid Credentials", Toast.LENGTH_LONG).show();
                                Intent back = new Intent(Forgot_password.this,Register_Cust.class);
                                startActivity(back);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                ref.addListenerForSingleValueEvent(eventListener);
            }
        });
    }
}
