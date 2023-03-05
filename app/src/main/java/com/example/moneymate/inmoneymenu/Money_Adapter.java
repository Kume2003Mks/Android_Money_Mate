package com.example.moneymate.inmoneymenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymate.R;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class Money_Adapter extends RecyclerView.Adapter<Money_Adapter.MyViewHolder_money> {

    Context context;
    ArrayList id;
    ArrayList title;
    ArrayList amount;
    ArrayList type;
    ArrayList category;
    ArrayList date;
    ArrayList time;

    public Money_Adapter(Context context, ArrayList id,
                         ArrayList title, ArrayList amount,
                         ArrayList type, ArrayList category,
                         ArrayList date, ArrayList time) {
        this.context = context;
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.time = time;
    }

    @NonNull
    @Override
    public MyViewHolder_money onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_money_list, parent, false);
        return new Money_Adapter.MyViewHolder_money(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_money holder, @SuppressLint("RecyclerView") int position) {
        holder.money_title.setText(String.valueOf(title.get(position)));
        holder.money_cate.setText(String.valueOf(category.get(position)));
        holder.money_time.setText(date.get(position) + " " + time.get(position));
        holder.money_type.setText(String.valueOf(type.get(position)));
        holder.money_amount.setText(String.valueOf(amount.get(position)));
        holder.money_layout.setOnClickListener(v -> {
            String DB_id = String.valueOf(id.get(position));
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Do you want to delete the data?");
            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                MoneyDB db = new MoneyDB(context);
                db.del_select_data(DB_id);
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> {
            });
            builder.create().show();
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder_money extends RecyclerView.ViewHolder {

        TextView money_title;
        TextView money_amount;
        TextView money_type;
        TextView money_cate;
        TextView money_time;
        LinearLayout money_layout;

        public MyViewHolder_money(@NonNull View itemView) {
            super(itemView);
            money_title = itemView.findViewById(R.id.money_title_textview);
            money_amount = itemView.findViewById(R.id.money_amount_textview);
            money_time = itemView.findViewById(R.id.money_time_textview);
            money_type = itemView.findViewById(R.id.money_type_textview);
            money_cate = itemView.findViewById(R.id.money_cate_textview);
            money_layout = itemView.findViewById(R.id.money_layout);
        }
    }
}