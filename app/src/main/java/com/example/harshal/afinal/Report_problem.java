package com.example.harshal.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Report_problem extends AppCompatActivity {

    EditText rt,prob;
    Button but;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = root.child("Problems");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        but = findViewById(R.id.button);
        rt = findViewById(R.id.editText3);
        prob = findViewById(R.id.editText2);


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String route = rt.getText().toString();
                String problem = prob.getText().toString();
                 if(!route.isEmpty() &&!problem.isEmpty()) {
                     ref.child(route).child("route").setValue(route);
                     ref.child(route).child("problem").setValue(problem);

                     Toast.makeText(Report_problem.this, "Problem Reported!", Toast.LENGTH_SHORT).show();
                     Intent back = new Intent(Report_problem.this, Employee_Dashboard.class);
                     startActivity(back);
                 }
                 else
                 {
                     rt.setError("Enter route id");
                     prob.setError("Enter the problem");
                 }
            }
        });

    }
}
