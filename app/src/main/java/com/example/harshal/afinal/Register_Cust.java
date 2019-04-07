package com.example.harshal.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register_Cust extends AppCompatActivity {

    Button submit;
    EditText aadhaar_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__cust);

        submit=findViewById(R.id.submit);
        aadhaar_no=findViewById(R.id.user);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aadhaar_card=aadhaar_no.getText().toString().trim();
                Intent i=new Intent(getBaseContext(),Register.class);
                i.putExtra("Adhar_no",aadhaar_card);
                startActivity(i);
            }
        });
    }
}
