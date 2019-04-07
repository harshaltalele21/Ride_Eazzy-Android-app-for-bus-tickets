package com.example.harshal.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class End_Journey extends AppCompatActivity {

    EditText from,to;
    Button proceed;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = root.child("Ticket_History");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end__journey);

        proceed = findViewById(R.id.button);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);

        SharedPreferences sharedp = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        final String usid = sharedp.getString("user_id","");
        final DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date today = new Date();

        String from_station=getIntent().getStringExtra("from_stat");
        final String tck_time=getIntent().getStringExtra("time");

        from.setText(from_station);

        final Activity activity=this;
        IntentIntegrator integrator= new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ref.child(usid).child(d.format(today)).child("from").setValue(from.getText().toString());
                ref.child(usid).child(tck_time).child("to").setValue(to.getText().toString());

                int f = Integer.parseInt(from.getText().toString());
                int t = Integer.parseInt(to.getText().toString());
                ref.child(usid).child(tck_time).child("amount").setValue(String.valueOf(t-f));

                Intent i = new Intent(End_Journey.this,Customer_Dashboard.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this,"You cancelled the scanning",Toast.LENGTH_LONG).show();
                Intent back = new Intent(End_Journey.this, New_ticket.class);
                //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(back);
                finish();


            } else{
                //Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                String qr = result.getContents();

                to.setEnabled(false);
                to.setText(qr);
                //ref.child(usid).child(d.format(today)).child("from").setValue(from.getText().toString());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
