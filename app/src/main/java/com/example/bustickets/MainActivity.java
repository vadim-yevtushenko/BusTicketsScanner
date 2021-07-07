package com.example.bustickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Ticket ticket;
    private Spinner spFrom;
    private Spinner spTo;
    private Button bDate;
    private Button bFind;
    private TextView tvFrom;
    private TextView tvTo;
    private TextView tvDate;
    private ArrayAdapter<City> adapter;
    public static final String TICKET_KEY = "ticket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initElements();
        initListeners();
        if (savedInstanceState != null){
            ticket = (Ticket) savedInstanceState.getSerializable(TICKET_KEY);
            if (ticket.getDate() != null) {
                String dayOfMonth = String.valueOf(ticket.getDate().get(Calendar.DAY_OF_MONTH));
                String month = String.valueOf(ticket.getDate().get(Calendar.MONTH) + 1);
                String year = String.valueOf(ticket.getDate().get(Calendar.YEAR));
                tvDate.setText(dayOfMonth + "." + month + "." + year);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, City.values());
        spFrom.setAdapter(adapter);
        spTo.setAdapter(adapter);
    }

    private void initListeners() {
        spFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ticket.setFrom(City.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ticket.setTo(City.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bDate.setOnClickListener(this::selectDate);
        bFind.setOnClickListener(this::findTickets);
    }

    private void findTickets(View view) {
        if (ticket.getDate() == null) {
            Toast toast = Toast.makeText(this, "select date", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 100);
            toast.show();
        } else {
            startSearchResultActivity(ticket);
        }
    }

    private void selectDate(View view) {
        Calendar calendar = new GregorianCalendar();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (DatePickerDialog.OnDateSetListener) (view1, year, month, dayOfMonth) -> {
                    tvDate.setText(dayOfMonth + "." + (month + 1) + "." + year);
                    ticket.setDate(new GregorianCalendar(year, month, dayOfMonth));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }

    private void initElements() {
        spFrom = findViewById(R.id.spFrom);
        spTo = findViewById(R.id.spTo);
        bDate = findViewById(R.id.bDate);
        bFind = findViewById(R.id.bFind);
        tvFrom = findViewById(R.id.tvFrom);
        tvTo = findViewById(R.id.tvTo);
        tvDate = findViewById(R.id.tvDate);
        ticket = new Ticket();
    }

    public void startSearchResultActivity(Ticket ticket) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra(TICKET_KEY, ticket);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle("Do you want exit?")
                .setMessage("App will be closed")
                .setPositiveButton("OK", (dialog, which) -> {
                    finish();
                })
                .setNeutralButton("CANCEL", (dialog, which) -> {

                });
        alertDialog.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(TICKET_KEY, ticket);
        super.onSaveInstanceState(outState);
    }
}