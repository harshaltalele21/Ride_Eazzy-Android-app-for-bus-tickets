package com.example.harshal.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class Admin_Dashboard extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__dashboard);

        mainGrid = findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;


            if (i == 0) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(Admin_Dashboard.this, Register_employee.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                });
            }

            if (i == 1) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(Admin_Dashboard.this, Block_user.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                });
            }

            if (i == 2) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(Admin_Dashboard.this, Revenue.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                });
            }

            if(i == 3){
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(Admin_Dashboard.this, Complaint.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                });
            }

        }
    }
}
