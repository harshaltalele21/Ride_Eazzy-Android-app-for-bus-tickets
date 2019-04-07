package com.example.harshal.afinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

public class Bus_search extends AppCompatActivity {

    Button submit;
    EditText route_id;
    ImageView imageView;
    Button end;
    Spinner s;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myref = root.child("Bus_Route");
    DatabaseReference tckref = root.child("Ticket_History");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search);

        submit=findViewById(R.id.submit);
        route_id=findViewById(R.id.route_id);
        imageView=findViewById(R.id.image);
        end=findViewById(R.id.end);

        s = (Spinner) findViewById(R.id.spinner);
        final String[] array = new String[]{"1","2","3","4","5","6","7","8","9"};
        final List<String> stat = new ArrayList<String>();
        String r_id=route_id.getText().toString().trim();


        //final String[] arraySpinner = new String[]{};
        /*DatabaseReference rt = myref.child(r_id);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                            *//*long cnt = dataSnapshot.getChildrenCount();
                            int c = (int) cnt;*//*
                    int i =0 ;
                    for(DataSnapshot ds : dataSnapshot.getChildren()){

                        String st = ds.getValue().toString();
                        stat.add(st);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        rt.addListenerForSingleValueEvent(eventListener);*/



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(Bus_search.this, position, Toast.LENGTH_SHORT).show();
                        //  String selectedItemText =parent.getItemAtPosition(position).toString();
                        // Notify the selected item text

                        String get = s.getSelectedItem().toString();
                        String qr_code=route_id.getText().toString()+get;
                        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                        try{
                            BitMatrix bitMatrix=multiFormatWriter.encode(qr_code, BarcodeFormat.QR_CODE,200,200);
                            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                            imageView.setImageBitmap(bitmap);
                        } catch( WriterException e){

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        //Toast.makeText(this, station_id[2], Toast.LENGTH_SHORT).show();


        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueEventListener eventListener1 = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot ds : dataSnapshot.getChildren()){
                                for(DataSnapshot ds2 : ds.getChildren()){

                                    if(ds2.child("route").getValue().toString().equals(route_id.getText().toString())){

                                        if(ds2.child("to").getValue().toString().equals("")){
                                            //Toast.makeText(Bus_search.this, "5", Toast.LENGTH_SHORT).show();
                                            String sp = s.getSelectedItem().toString();
                                            String to = route_id.getText().toString()+sp;
                                            //Toast.makeText(Bus_search.this, to, Toast.LENGTH_SHORT).show();
                                            DatabaseReference d = ds2.getRef();
                                            d.child("to").setValue(to);
                                            String from = ds2.child("from").getValue().toString();
                                            int amt = (Integer.parseInt(to)-Integer.parseInt(from));
                                            d.child("amount").setValue(String.valueOf(amt));

                                        }
                                    }
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                tckref.addListenerForSingleValueEvent(eventListener1);


                Toast.makeText(Bus_search.this, "Journey Successfully ended", Toast.LENGTH_SHORT).show();
                Intent i =  new Intent(Bus_search.this,Employee_Dashboard.class);
                startActivity(i);
                finish();
            }
        });

    }
}
