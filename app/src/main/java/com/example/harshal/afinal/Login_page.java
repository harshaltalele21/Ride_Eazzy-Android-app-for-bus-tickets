package com.example.harshal.afinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_page extends AppCompatActivity {

    Button login_btn;
    TextView user_id;
    TextView password;
    TextView register;
    TextView forgot;
    Spinner s;
    ProgressDialog dialog;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_btn = findViewById(R.id.Login_Btn);
        user_id = findViewById(R.id.user);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        forgot = findViewById(R.id.forgot_password);
        s = (Spinner) findViewById(R.id.spinner);


        String[] arraySpinner = new String[]{
                "Customer", "Employee", "Admin"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=user_id.getText().toString().trim();
                String pass=password.getText().toString().trim();

                String type = s.getSelectedItem().toString().trim();
                dialog= new ProgressDialog(Login_page.this);
                dialog.setMessage("Authenticating");

                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                dialog.setCancelable(false);
                if(type.equals("Customer"))
                {
                    DatabaseReference cust = root.child("Customer");

                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                int flag = 0;
                                String ent_user = user_id.getText().toString().trim();
                                String ent_pass = password.getText().toString().trim();
                                for(DataSnapshot ds : dataSnapshot.getChildren()){

                                    String us = ds.child("user_id").getValue().toString();
                                    String ps = ds.child("password").getValue().toString();

                                    if(ent_pass.equals(ps) && ent_user.equals(us)){

                                        SharedPreferences sharedp = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedp.edit();
                                        editor.putString("user_id",us);
                                        editor.apply();
                                        dialog.dismiss();
                                        Intent i=new Intent(Login_page.this,Customer_Dashboard.class);
                                        startActivity(i);
                                       // finish();
                                        flag = 1;
                                        break;
                                    }

                                }
                                if(flag == 0){
                                    Toast.makeText(Login_page.this,"Wrong Credentials!",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    cust.addListenerForSingleValueEvent(eventListener);
                }
                else if(type.equals("Employee"))
                {
                    DatabaseReference emp = root.child("Employee");

                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                int flag = 0;
                                String ent_user = user_id.getText().toString().trim();
                                String ent_pass = password.getText().toString().trim();
                                for(DataSnapshot ds : dataSnapshot.getChildren()){

                                    String us = ds.child("user_id").getValue().toString();
                                    String ps = ds.child("password").getValue().toString();

                                    if(ent_pass.equals(ps) && ent_user.equals(us)){

                                        SharedPreferences sharedp = getSharedPreferences("EmployeeInfo",Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedp.edit();
                                        editor.putString("user_id",us);
                                        editor.apply();

                                        Intent i=new Intent(Login_page.this,Employee_Dashboard.class);
                                        startActivity(i);
                                        flag = 1;
                                        break;
                                    }

                                }
                                if(flag == 0){
                                    Toast.makeText(Login_page.this,"Wrong Credentials!",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    emp.addListenerForSingleValueEvent(eventListener);
                }
                else if(type.equals("Admin"))
                {
                    if( id.equals("root")){
                        Toast.makeText(Login_page.this,"Login Successful",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Login_page.this,Admin_Dashboard.class);
                        startActivity(i);
                        dialog.dismiss();
                    } else{
                        Toast.makeText(Login_page.this,"Login Unsuccessful",Toast.LENGTH_LONG).show();
                       // dialog.dismiss();
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login_page.this,Register_Cust.class);
                startActivity(i);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login_page.this,Forgot_password.class);
                startActivity(i);
            }
        });
    }
}
