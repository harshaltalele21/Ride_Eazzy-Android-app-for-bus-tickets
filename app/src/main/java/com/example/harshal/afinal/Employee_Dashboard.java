package com.example.harshal.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class Employee_Dashboard extends AppCompatActivity {

    GridLayout mainGrid;
    SharedPreferences prf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__dashboard);


        //setContentView(R.layout.activity_employee_dashboard);
        mainGrid=findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for(int i=0;i<mainGrid.getChildCount();i++){

            CardView cardView=(CardView)mainGrid.getChildAt(i);
            final int finalI=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(MainActivity.this,"Clicked at"+finalI,Toast.LENGTH_LONG).show();
                    if(finalI==0){
                        Intent bus_search=new Intent(Employee_Dashboard.this,Bus_search.class);
                        startActivity(bus_search);
                    }
                    else if(finalI==1){
                        Intent report_prob=new Intent(Employee_Dashboard.this,Report_problem.class);
                        startActivity(report_prob);
                    }
                    /*else if(finalI==2){
                        Intent block_user=new Intent(Employee_Dashboard.this,Report_Problem.class);
                        startActivity(block_user);
                    }*/
                    else if(finalI==2){
                        prf=getSharedPreferences("EmployeeInfo",MODE_PRIVATE);
                        SharedPreferences.Editor editor= prf.edit();
                        editor.clear();
                        editor.apply();
                        Intent log_out=new Intent(Employee_Dashboard.this,Login_page.class);
                        startActivity(log_out);
                        finish();
                    }
                }
            });
        }
    }
}
