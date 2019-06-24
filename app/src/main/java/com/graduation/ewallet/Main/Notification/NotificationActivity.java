package com.graduation.ewallet.Main.Notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.graduation.ewallet.R;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView container;
    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        container = findViewById(R.id.Container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        container.setLayoutManager(linearLayoutManager);
        adapter = new NotificationAdapter(this);

    }
}
