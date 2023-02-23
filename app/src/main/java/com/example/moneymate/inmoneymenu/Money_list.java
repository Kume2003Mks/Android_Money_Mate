package com.example.moneymate.inmoneymenu;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moneymate.R;

public class Money_list extends AppCompatActivity {

    ImageView NoData_imv;
    TextView NoData_tw;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_list);

        NoData_imv = findViewById(R.id.Nodata_IV2);
        NoData_tw = findViewById(R.id.Nodata_TW2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.money_swipeRefreshLayout2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List");

        MoneyModel MM = new MoneyModel(this);

        Cursor cursor = MM.getCursor();

        show_noData_alert(cursor);

        recyclerView = findViewById(R.id.list_money);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(MM.getMoney_adapter());

        swipeRefreshLayout.setOnRefreshListener(() -> {
            recreate();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void show_noData_alert(Cursor cursor){
        if (cursor.getCount() == 0) {
            NoData_imv.setVisibility(View.VISIBLE);
            NoData_tw.setVisibility(View.VISIBLE);
        } else {
            NoData_imv.setVisibility(View.GONE);
            NoData_tw.setVisibility(View.GONE);
        }

    }
}