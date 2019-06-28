package com.graduation.ewallet.Main.TransactionHistroy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.graduation.ewallet.R;

public class TransactionHistoryActivity extends AppCompatActivity {

    Button btnSent, btnReceived;
    TextView tvTotal;
    RecyclerView container;
    TransactionHistoryAdapter adapter;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        btnSent = findViewById(R.id.btnSent);
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTransactionType(v);
            }
        });
        btnReceived = findViewById(R.id.btnReceived);
        btnReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTransactionType(v);
            }
        });
        btnSent.performClick();
        tvTotal = findViewById(R.id.tvTotal);
        container = findViewById(R.id.Container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        container.setLayoutManager(linearLayoutManager);
        adapter = new TransactionHistoryAdapter(this, tag);
    }

    void switchTransactionType(View v){

        btnSent.setTextColor(getColor(R.color.white));
        btnSent.setBackgroundResource(R.color.transparent);
        btnReceived.setTextColor(getColor(R.color.white));
        btnReceived.setBackgroundResource(R.color.transparent);

        switch (v.getId()) {
            case R.id.btnSent:
                btnSent.setTextColor(getColor(R.color.colorAccent));
                btnSent.setBackgroundResource(R.drawable.round_left_choice_selected);
                tag = "sent";
                setRecycler(v);
                break;
            case R.id.btnReceived:
                btnReceived.setTextColor(getColor(R.color.colorPrimary));
                btnReceived.setBackgroundResource(R.drawable.round_right_choice_selected);
                tag = "received";
                setRecycler(v);
                break;
        }
    }

    void setRecycler(View view){

    }
}
