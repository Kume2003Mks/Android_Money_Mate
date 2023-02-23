package com.example.moneymate.inmoneymenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymate.MainActivity;
import com.example.moneymate.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("resource")
public class Money_I_and_E extends AppCompatActivity {

    private Spinner spinner_list;
    private TextView cate_text;
    private RadioButton radioButton1, radioButton2;
    private EditText eTitle;
    private EditText eAmount;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_iand_e);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Income and Expenses");

        eTitle = findViewById(R.id.title_money);
        eAmount = findViewById(R.id.money_amount);
        cate_text = findViewById(R.id.cate_text);
        radioButton1 = findViewById(R.id.income_radioButton);
        radioButton2 = findViewById(R.id.expense_radioButton);

        spinner_list = findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_list.setAdapter(adapter);

        spinner_list.setVisibility(View.INVISIBLE);
        cate_text.setVisibility(View.INVISIBLE);

    }

    public void save_IE(View view) {
        String title, t_amount, t_type = null, t_cate = "Income";
        double r_amount;

        MoneyDB Mdb = new MoneyDB(getApplicationContext());

        title = eTitle.getText().toString();
        t_amount = eAmount.getText().toString();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getApplicationContext(), "Enter Title", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(t_amount)) {
            Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_SHORT).show();
            return;
        }

        r_amount = Double.parseDouble(t_amount);

        if (radioButton1.isChecked()) {
            t_type = "Income";
            t_cate = "Income";
        } else if (radioButton2.isChecked()) {
            r_amount *= -1D;
            t_type = "Expense";
            t_cate = spinner_list.getSelectedItem().toString();
        }
        Mdb.addIE(title, r_amount, t_type, t_cate, getDate(), getTime());
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private String getDate() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }

    private String getTime() {
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
    }

    @SuppressLint("NonConstantResourceId")
    public void show_category(View view) {
        boolean check = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.income_radioButton:
                if (check) {
                    spinner_list.setVisibility(View.INVISIBLE);
                    cate_text.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.expense_radioButton:
                if (check) {
                    spinner_list.setVisibility(View.VISIBLE);
                    cate_text.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}