package com.example.moneymate.main_ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moneymate.R;
import com.example.moneymate.databinding.FragmentHomeBinding;
import com.example.moneymate.inmoneymenu.MoneyDB;
import com.example.moneymate.inmoneymenu.MoneyModel;

import java.util.List;

public class HomeFragment extends Fragment {

    ImageView NoData_imv;
    TextView NoData_tw;

    RecyclerView recyclerView;
    MoneyDB moneyDB;
    TextView amountText;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NoData_imv = root.findViewById(R.id.Nodata_IV2);
        NoData_tw = root.findViewById(R.id.Nodata_TW2);

        moneyDB = new MoneyDB(getContext());
        MoneyModel MM = new MoneyModel(getContext());
        amountText = root.findViewById(R.id.amount_text);

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.money_swipeRefreshLayout);

        Cursor cursor = MM.getCursor();

        show_noData_alert(cursor,root);

        showBalance();

        recyclerView = root.findViewById(R.id.money_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(MM.getMoney_adapter());
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getActivity().recreate();
            swipeRefreshLayout.setRefreshing(false);
        });

        return root;
    }

    private void show_noData_alert(Cursor cursor,View view) {
        if (cursor.getCount() == 0) {
            NoData_imv.setVisibility(View.VISIBLE);
            NoData_tw.setVisibility(View.VISIBLE);
        } else {
            NoData_imv.setVisibility(View.GONE);
            NoData_tw.setVisibility(View.GONE);
        }
    }

    private void showBalance() {
        double sum = 0;
        List<Double> amount = moneyDB.getAmount();
        for (int i = 0; i < amount.size(); i++) {
            sum = sum + amount.get(i);
        }
        @SuppressLint("DefaultLocale") String stSet = String.format("%.2f THB", sum);
        amountText.setText(stSet);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}