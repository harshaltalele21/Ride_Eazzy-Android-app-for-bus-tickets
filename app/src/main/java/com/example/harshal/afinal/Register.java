package com.example.harshal.afinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Register extends AppCompatActivity {
    Button register,submit;
    EditText nm,mobileno,date_of_birth,uid,pass,adhar;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myref = root.child("Customer");
    DatabaseReference ad_list = root.child("Aadhaar_List");
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nm=findViewById(R.id.name);
        mobileno=findViewById(R.id.mob_no);
        date_of_birth=findViewById(R.id.dob);
        uid=findViewById(R.id.user);
        pass=findViewById(R.id.password);
        adhar=findViewById(R.id.aahdaar);
        register=findViewById(R.id.register);
        String aadhaar_card=getIntent().getStringExtra("Adhar_no");
        adhar.setText(aadhaar_card);
        adhar.setEnabled(false);
        dialog= new ProgressDialog(Register.this);
        dialog.setMessage("Please Wait!!");
       // dialog.setTitle("Progress Dialog");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        dialog.setCancelable(false);

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = root.child("Aadhaar_List");

        ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            int flag = 0;

                            String ad_entered = adhar.getText().toString().trim();
                            for(DataSnapshot ds : dataSnapshot.getChildren()){

                                String ad = ds.child("aadhaar_num").getValue().toString();


                                if(ad_entered.equals(ad)) {

                                    flag = 1;
                                    String name = ds.child("aadhaar_name").getValue().toString();
                                    String dob = ds.child("aadhaar_dob").getValue().toString();
                                    String mob = ds.child("aadhaar_mob").getValue().toString();
                                    nm.setText(name);
                                    nm.setEnabled(false);
                                    date_of_birth.setText(dob);
                                    date_of_birth.setEnabled(false);
                                    mobileno.setText(mob);
                                    mobileno.setEnabled(false);
                                    dialog.dismiss();
                                    break;

                                }
                                else{
                                    flag =0;
                                }
                            }
                            if(flag==0){
                                Toast.makeText(Register.this, "Enter a valid Aadhaar Number", Toast.LENGTH_LONG).show();
                                Intent back = new Intent(Register.this,Register_Cust.class);
                                startActivity(back);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                ref.addListenerForSingleValueEvent(eventListener);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference root = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref = root.child("Customer");

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            int user_flag = 0;
                            String us = uid.getText().toString().trim();
                            String exists_adh = adhar.getText().toString().trim();

                            for(DataSnapshot ds : dataSnapshot.getChildren()){

                                String exits_us = ds.child("user_id").getValue().toString();
                                String adh = ds.child("aadhaar_no").getValue().toString();
                                 if(exists_adh.equals(adh)){
                                    Toast.makeText(Register.this, "Aadhaar number already Registered!", Toast.LENGTH_SHORT).show();
                                    user_flag = 1;
                                    break;
                                }
                                else if(us.equals(exits_us)){
                                    Toast.makeText(Register.this, "User Id already exists!", Toast.LENGTH_SHORT).show();
                                    user_flag = 1;
                                    break;
                                }

                            }
                            if(user_flag != 1){
                                reg_user();
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

    public void reg_user(){
        CustInfo ci = new CustInfo();
        ci.setAadhaar_no(adhar.getText().toString().trim());
        ci.setDate_of_birth(date_of_birth.getText().toString().trim());
        ci.setNaam(nm.getText().toString().trim());
        ci.setMobile_no(mobileno.getText().toString().trim());
        ci.setUser_id(uid.getText().toString().trim());
        ci.setPassword(pass.getText().toString().trim());

        myref.child(adhar.getText().toString().trim()).setValue(ci);

        Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Register.this, Login_page.class);
        startActivity(i);
        finish();
        Toast.makeText(Register.this, "Login to continue!", Toast.LENGTH_SHORT).show();
    }
}
