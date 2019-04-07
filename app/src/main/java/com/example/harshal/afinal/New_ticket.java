package com.example.harshal.afinal;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class New_ticket extends AppCompatActivity {

    TextView text;
    EditText from,to;
    Button end;
    String route,q;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = root.child("Ticket_History");
    DatabaseReference rtref = root.child("Bus_Route");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ticket);

        end = findViewById(R.id.button);
        text = findViewById(R.id.text);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);

        SharedPreferences sharedp = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        final String usid = sharedp.getString("user_id","");
        final DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date today = new Date();
        final String time = d.format(today);
        final Activity activity=this;

        IntentIntegrator integrator= new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

        /*ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){

                        if(ds.child("route_num").getValue().toString().equals(route)){
                            String s = ds.child(q).getValue().toString();
                            from.setText(s);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        rtref.addListenerForSingleValueEvent(eventListener);*/


        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.child(usid).child(time).child("from").setValue(from.getText().toString());
                ref.child(usid).child(time).child("to").setValue("");
                ref.child(usid).child(time).child("amount").setValue("");
                ref.child(usid).child(time).child("route").setValue(route);
                String frm=from.getText().toString().trim();
                Intent i=new Intent(getBaseContext(),End_Journey.class);
                i.putExtra("from_stat",frm);
                i.putExtra("time",time);
                startActivity(i);


            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this,"You cancelled the scanning",Toast.LENGTH_LONG).show();
                Intent back = new Intent(New_ticket.this, Customer_Dashboard.class);
                //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(back);
                finish();


            } else{
                //Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                String qr = result.getContents();
                q = qr;
                from.setEnabled(false);
                from.setText(qr);
                route = qr.substring(0,3);
                Notification.Builder builder = new Notification.Builder(this);
                builder.setSmallIcon(R.drawable.icon);

                builder.setContentTitle("Ride Eazzy");
                builder.setContentText("Route ID :"+route+" Station ID :"+qr.substring(3,4));
                builder.setSubText("Tap to end the Journey");

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1,builder.build());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
