package com.example.bustickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class FinalActivity extends AppCompatActivity {
    private Ticket ticket;
    private TextView tvTicket;
    private Button bBuy;
    private Button bBack;
    private RadioGroup rgBaggage;
    private RadioButton rbNan;
    private RadioButton rbSmall;
    private RadioButton rbBig;
    private CheckBox cbCoffee;
    private CheckBox cbTea;
    private CheckBox cbJuice;
    private CheckBox cbSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        if (intent != null) {
            ticket = (Ticket) intent.getSerializableExtra(MainActivity.TICKET_KEY);
        }
        if (savedInstanceState != null) {
            ticket = (Ticket) savedInstanceState.getSerializable(MainActivity.TICKET_KEY);
        }
        initElements();
        setTicketView();
        initListeners();
        initCheckBoxes();
    }

    private void initCheckBoxes() {
        cbCoffee.setOnClickListener(v -> {
            if (cbCoffee.isChecked()) {
                ticket.getFoodStaffs().add(FoodStaff.COFFEE);
                ticket.setPrice(ticket.getPrice() + ticket.getFoodAndDrinks().get(FoodStaff.COFFEE));
                setTicketView();
            } else {
                ticket.getFoodStaffs().remove(FoodStaff.COFFEE);
                ticket.setPrice(ticket.getPrice() - ticket.getFoodAndDrinks().get(FoodStaff.COFFEE));
                setTicketView();
            }
        });

        cbTea.setOnClickListener(v -> {
            if (cbTea.isChecked()) {
                ticket.getFoodStaffs().add(FoodStaff.TEA);
                ticket.setPrice(ticket.getPrice() + ticket.getFoodAndDrinks().get(FoodStaff.TEA));
                setTicketView();
            } else {
                ticket.getFoodStaffs().remove(FoodStaff.TEA);
                ticket.setPrice(ticket.getPrice() - ticket.getFoodAndDrinks().get(FoodStaff.TEA));
                setTicketView();
            }
        });

        cbJuice.setOnClickListener(v -> {
            if (cbJuice.isChecked()) {
                ticket.getFoodStaffs().add(FoodStaff.JUICE);
                ticket.setPrice(ticket.getPrice() + ticket.getFoodAndDrinks().get(FoodStaff.JUICE));
                setTicketView();
            } else {
                ticket.getFoodStaffs().remove(FoodStaff.JUICE);
                ticket.setPrice(ticket.getPrice() - ticket.getFoodAndDrinks().get(FoodStaff.JUICE));
                setTicketView();
            }
        });

        cbSnacks.setOnClickListener(v -> {
            if (cbSnacks.isChecked()) {
                ticket.getFoodStaffs().add(FoodStaff.SNACKS);
                ticket.setPrice(ticket.getPrice() + ticket.getFoodAndDrinks().get(FoodStaff.SNACKS));
                setTicketView();
            } else {
                ticket.getFoodStaffs().remove(FoodStaff.SNACKS);
                ticket.setPrice(ticket.getPrice() - ticket.getFoodAndDrinks().get(FoodStaff.SNACKS));
                setTicketView();
            }
        });

    }

    private void initListeners() {
        rbNan.setChecked(true);
        rgBaggage.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbNan) {
                ticket.setPrice(ticket.getPrice() - ticket.getBaggage());
                ticket.setBaggage(Ticket.NO_BAGGAGE);
                setTicketView();
            } else if (checkedId == R.id.rbSmall) {
                ticket.setPrice(ticket.getPrice() - ticket.getBaggage());
                ticket.setBaggage(Ticket.SMALL_BAGGAGE);
                ticket.setPrice(ticket.getPrice() + ticket.getBaggage());
                setTicketView();
            } else if (checkedId == R.id.rbBig) {
                ticket.setPrice(ticket.getPrice() - ticket.getBaggage());
                ticket.setBaggage(Ticket.BIG_BAGGAGE);
                ticket.setPrice(ticket.getPrice() + ticket.getBaggage());
                setTicketView();
            }
        });

        bBack.setOnClickListener(this::moveBack);
        bBuy.setOnClickListener(this::buyTicket);
    }

    private void buyTicket(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void moveBack(View view) {
        changeDBValues();
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra(MainActivity.TICKET_KEY, ticket);
        startActivity(intent);
        finish();
    }

    private void changeDBValues() {
        for (Ticket ticket1 : DBTickets.tickets.keySet()) {
            if (ticket.getFrom().equals(ticket1.getFrom())
                    && ticket.getTo().equals(ticket1.getTo())
                    && ticket.getDate().get(Calendar.YEAR) == ticket1.getDate().get(Calendar.YEAR)
                    && ticket.getDate().get(Calendar.MONTH) == ticket1.getDate().get(Calendar.MONTH)
                    && ticket.getDate().get(Calendar.DAY_OF_MONTH) == ticket1.getDate().get(Calendar.DAY_OF_MONTH)
                    && ticket.getDate().get(Calendar.HOUR_OF_DAY) == ticket1.getDate().get(Calendar.HOUR_OF_DAY)
                    && ticket.getDate().get(Calendar.MINUTE) == ticket1.getDate().get(Calendar.MINUTE)) {
                DBTickets.tickets.put(ticket1, DBTickets.tickets.get(ticket1) + 1);
            }
        }
    }

    private void setTicketView() {
        String date = ticket.getDate().get(Calendar.DAY_OF_MONTH) + "." + ticket.getDate().get(Calendar.MONTH) + "." + ticket.getDate().get(Calendar.YEAR);
        String time = ticket.getDate().get(Calendar.HOUR_OF_DAY) + ":" + ticket.getDate().get(Calendar.MINUTE);
        String baggage = getStringBaggage();
        String add = getStringAddPay();
        String tvString = String.format("From: %s   To: %s\nDate: %s   Time: %s\nClass: %s   Toilet: %b\nBaggage: %s%s\nPrice: %.2f$",
                ticket.getFrom().getName(),
                ticket.getTo().getName(),
                date,
                time,
                ticket.getSeatClass(),
                ticket.isToilet(),
                baggage,
                add,
                ticket.getPrice());

        tvTicket.setText(tvString);
    }

    private String getStringAddPay() {
        if (ticket.getFoodStaffs().size() < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nAdd: ");
        for (FoodStaff foodStaff : ticket.getFoodStaffs()) {
            stringBuilder.append(foodStaff.getName()).append("|");
        }
        return stringBuilder.toString();
    }

    private String getStringBaggage() {
        if (ticket.getBaggage() == Ticket.NO_BAGGAGE) {
            return "no baggage";
        } else if (ticket.getBaggage() == Ticket.SMALL_BAGGAGE) {
            return "small";
        } else
            return "big";
    }

    private void initElements() {
        tvTicket = findViewById(R.id.tvTicket);
        bBuy = findViewById(R.id.bBuy);
        bBack = findViewById(R.id.bBack);
        rgBaggage = findViewById(R.id.rgBaggage);
        rbNan = findViewById(R.id.rbNan);
        rbSmall = findViewById(R.id.rbSmall);
        rbBig = findViewById(R.id.rbBig);
        cbCoffee = findViewById(R.id.cbCoffee);
        cbTea = findViewById(R.id.cbTea);
        cbJuice = findViewById(R.id.cbJuice);
        cbSnacks = findViewById(R.id.cbSnacks);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(MainActivity.TICKET_KEY, ticket);
        super.onSaveInstanceState(outState);
    }
}