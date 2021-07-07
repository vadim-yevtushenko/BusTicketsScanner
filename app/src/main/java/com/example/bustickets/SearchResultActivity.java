package com.example.bustickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SearchResultActivity extends AppCompatActivity {

    private Ticket ticket;
    private Map<Ticket, Integer> searchResultTickets;
    ArrayList<TextView> views;
    private Button bSelect;
    private Button bBack;
    private TextView tvT1;
    private TextView tvT2;
    private TextView tvT3;
    private TextView tvT4;
    private TextView tvHeader;
    private boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        if (intent != null) {
            ticket = (Ticket) intent.getSerializableExtra(MainActivity.TICKET_KEY);
        }
        initElements();
        fillViews();
        fillMapSearchResult();
        setResultView();
        bBack.setOnClickListener(this::moveBack);

    }

    private void moveBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setResultView() {
        int countViews = 0;
        if (searchResultTickets.size() == 0) {
            tvHeader.setText("Tickets on " + ticket.getDate().get(Calendar.DAY_OF_MONTH) + "." + ticket.getDate().get(Calendar.MONTH) + "." + ticket.getDate().get(Calendar.YEAR));
            tvT1.setText("No tickets on this date");
        } else {
            tvHeader.setText("Tickets on " + ticket.getDate().get(Calendar.DAY_OF_MONTH) + "." + ticket.getDate().get(Calendar.MONTH) + "." + ticket.getDate().get(Calendar.YEAR));
            for (Map.Entry<Ticket, Integer> pair : searchResultTickets.entrySet()) {
                Ticket ticketTemp = pair.getKey();
                if (DBTickets.tickets.get(ticketTemp) > 0) {
                    String date = ticketTemp.getDate().get(Calendar.DAY_OF_MONTH) + "." + ticketTemp.getDate().get(Calendar.MONTH) + "." + ticketTemp.getDate().get(Calendar.YEAR);
                    String time = ticketTemp.getDate().get(Calendar.HOUR_OF_DAY) + ":" + ticketTemp.getDate().get(Calendar.MINUTE);
                    String tvString = String.format("From: %s   To: %s\nDate: %s   Time: %s\nClass: %s   Toilet: %b\nQuantity: %d   Price: %.2f$",
                            ticketTemp.getFrom().getName(),
                            ticketTemp.getTo().getName(),
                            date,
                            time,
                            ticketTemp.getSeatClass(),
                            ticketTemp.isToilet(),
                            pair.getValue(),
                            ticketTemp.getPrice());
                    views.get(countViews).setText(tvString);
                    countViews++;
                }
            }
        }
        initListeners();
    }

    private void initListeners() {
        bSelect.setOnClickListener(v -> {
            if (isSelected) {
                int value = DBTickets.tickets.get(ticket);
                DBTickets.tickets.put(ticket, value - 1);
                Intent intent = new Intent(this, FinalActivity.class);
                intent.putExtra(MainActivity.TICKET_KEY, ticket);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this, "select ticket", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 100);
                toast.show();
            }
        });

        final int[] count = {0};
        for (Ticket ticket1 : searchResultTickets.keySet()) {
            TextView textView = views.get(count[0]);
            textView.setOnClickListener(v -> {
                ticket = ticket1;
                textView.setTextColor(Color.parseColor("#FF000000"));
                for (TextView view : views) {
                    if (!textView.equals(view)) {
                        view.setTextColor(Color.parseColor("#717171"));
                    }
                }
                isSelected = true;
            });
            count[0]++;
        }

    }

    private void fillMapSearchResult() {
        for (Map.Entry<Ticket, Integer> pair : DBTickets.tickets.entrySet()) {
            Ticket ticket1 = pair.getKey();
            if (ticket.getFrom().equals(ticket1.getFrom())
                    && ticket.getTo().equals(ticket1.getTo())
                    && ticket.getDate().get(Calendar.YEAR) == ticket1.getDate().get(Calendar.YEAR)
                    && ticket.getDate().get(Calendar.MONTH) == ticket1.getDate().get(Calendar.MONTH)
                    && ticket.getDate().get(Calendar.DAY_OF_MONTH) == ticket1.getDate().get(Calendar.DAY_OF_MONTH)) {
                searchResultTickets.put(pair.getKey(), pair.getValue());
            }
        }
    }

    private void initElements() {
        searchResultTickets = new TreeMap<>((o1, o2) -> {
            if (o1.getDate().before(o2.getDate())) {
                return -1;
            } else if (o1.getDate().after(o2.getDate())) {
                return 1;
            } else return 0;
        });
        views = new ArrayList<>();
        bSelect = findViewById(R.id.bSelect);
        bBack = findViewById(R.id.bBack);
        tvT1 = findViewById(R.id.tvT1);
        tvT2 = findViewById(R.id.tvT2);
        tvT3 = findViewById(R.id.tvT3);
        tvT4 = findViewById(R.id.tvT4);
        tvHeader = findViewById(R.id.tvHeader);
    }

    private void fillViews() {
        views.add(tvT1);
        views.add(tvT2);
        views.add(tvT3);
        views.add(tvT4);
    }

}