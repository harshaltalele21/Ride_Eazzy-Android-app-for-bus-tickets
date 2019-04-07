package com.example.harshal.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class Customer_Dashboard extends AppCompatActivity {

    GridLayout mainGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__dashboard);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        SharedPreferences sharedp = getSharedPreferences("userInfo",Context.MODE_PRIVATE);

        String usid = sharedp.getString("user_id","");

        //Toast.makeText(this, usid, Toast.LENGTH_SHORT).show();
        //Set Event
        setSingleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(Customer_Dashboard.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(Customer_Dashboard.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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


                        Intent intent = new Intent(Customer_Dashboard.this, New_ticket.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                });
            }

            if (i == 1) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(Customer_Dashboard.this, History.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                });
            }

            if (i == 2) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(Customer_Dashboard.this, Payment.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                });
            }

            if (i == 3) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(Customer_Dashboard.this, "Successfully Logged OUT!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Customer_Dashboard.this, Welcome.class);
                        startActivity(i);
                        finish();

                    }
                });
            }

        }


    }
}
