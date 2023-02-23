package com.example.moneymate.inmoneymenu;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymate.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class Money_summary extends AppCompatActivity {

    private PieChart pieChart;
    MoneyDB Mdb;
    public static final int[] MY_COLORS = {
            rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db"), rgb("#ff2cec")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_summary);
        Mdb = new MoneyDB(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Summary");

        pieChart = findViewById(R.id.pieChart_sum);
        showBalance();
        createChart();
    }

    private void createChart(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        MoneyModel mm = new MoneyModel(getApplicationContext());
        ArrayList<Object> label = mm.getCategory();
        ArrayList<Object> value = mm.getAmount();

        entries.add(new PieEntry(general_data(),"General"));
        entries.add(new PieEntry(food_data(),"Food"));
        entries.add(new PieEntry(shopping_data(),"Shopping"));
        entries.add(new PieEntry(fare_data(),"Fare"));
        entries.add(new PieEntry(utilities_data(),"Utilities"));

        PieDataSet pieDataSet = new PieDataSet(entries," ");
        pieDataSet.setColors(MY_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(16);
        pieDataSet.setValueFormatter(new PercentFormatter());
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(50);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setTransparentCircleRadius(55);
        pieChart.animateY(1000);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(Color.DKGRAY);
        legend.setTextSize(12);
        legend.setFormSize(15);
    }

    private void showBalance() {
        TextView textView = findViewById(R.id.amount_text_SP);
        double sum = 0;
        List<Double> amount = Mdb.getAmount();
        for (int i = 0; i < amount.size(); i++) {
            sum = sum + amount.get(i);
        }
        @SuppressLint("DefaultLocale") String stSet = String.format("%.2f THB", sum);
        textView.setText(stSet);

    }
    private float general_data() {
        double sum = 0;
        List<Double> amount = Mdb.get_General_data();
        for (int i = 0; i < amount.size(); i++) {
            sum = sum + amount.get(i);
        }
        sum = sum* -1;
        return (float) sum;
    }

    private float food_data() {
        double sum = 0;
        List<Double> amount = Mdb.get_Food_data();
        for (int i = 0; i < amount.size(); i++) {
            sum = sum + amount.get(i);
        }
        sum = sum* -1;
        return (float) sum;
    }

    private float shopping_data() {
        double sum = 0;
        List<Double> amount = Mdb.get_Shopping_data();
        for (int i = 0; i < amount.size(); i++) {
            sum = sum + amount.get(i);
        }
        sum = sum* -1;
        return (float) sum;
    }

    private float fare_data() {
        double sum = 0;
        List<Double> amount = Mdb.get_Fare_data();
        for (int i = 0; i < amount.size(); i++) {
            sum = sum + amount.get(i);
        }
        sum = sum* -1;
        return (float) sum;
    }

    private float utilities_data() {
        double sum = 0;
        List<Double> amount = Mdb.get_Utilities_data();
        for (int i = 0; i < amount.size(); i++) {
            sum = sum + amount.get(i);
        }
        sum = sum* -1;
        return (float) sum;
    }
}