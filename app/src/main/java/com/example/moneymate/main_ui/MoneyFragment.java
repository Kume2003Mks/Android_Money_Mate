package com.example.moneymate.main_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.moneymate.R;
import com.example.moneymate.databinding.FragmentMoneyBinding;
import com.example.moneymate.inmoneymenu.Money_I_and_E;
import com.example.moneymate.inmoneymenu.Money_list;
import com.example.moneymate.inmoneymenu.Money_summary;

public class MoneyFragment extends Fragment {

    private View view;
    private FragmentMoneyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMoneyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        this.view = root;

        Money_menu_I_and_E();
        Money_menu_plan();
        Money_menu_summary();

        return root;
    }

    private void Money_menu_I_and_E() {
        CardView cardView = view.findViewById(R.id.monney_I_and_E);
        cardView.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), Money_I_and_E.class));
        });
    }

    private void Money_menu_plan() {
        CardView cardView = view.findViewById(R.id.monney_mp);
        cardView.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), Money_list.class));
        });
    }

    private void Money_menu_summary() {
        CardView cardView = view.findViewById(R.id.money_summary);
        cardView.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), Money_summary.class));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}