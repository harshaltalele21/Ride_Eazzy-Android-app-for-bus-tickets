package com.example.harshal.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private ListView lvTicket;
    private ticket_adapter adaptor;
    private List<ticket_list> mTicketlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvTicket= (ListView)findViewById(R.id.listview_ticket);

        mTicketlist=new ArrayList<>();

        mTicketlist.add(new ticket_list(1,"12-04-2019","Kothrud-Shivaji Nagar",20));
        mTicketlist.add(new ticket_list(2,"12-02-2019","Kothrud-MANAPA",20));
        mTicketlist.add(new ticket_list(3,"13-04-2019","Kothrud-Balewadi",50));
        mTicketlist.add(new ticket_list(4,"02-04-2019","Shivaji Nagar-ABC",20));
        mTicketlist.add(new ticket_list(5,"28-05-2019","Shivaji Nagar-Katraj",20));
        mTicketlist.add(new ticket_list(6,"11-06-2019","Kothrud-Katraj",20));
        mTicketlist.add(new ticket_list(7,"24-07-2019","Katraj-Shivaji Nagar",20));

        adaptor=new ticket_adapter(getApplicationContext(),mTicketlist);
        lvTicket.setAdapter(adaptor);

        lvTicket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked ticket ID ="+view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
