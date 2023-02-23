package com.example.moneymate.inmoneymenu;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;

public class MoneyModel {
    ArrayList<Object> id_db;
    ArrayList<Object> title_db;
    ArrayList<Object> amount_db;
    ArrayList<Object> type_db;
    ArrayList<Object> category_db;
    ArrayList<Object> date_db;
    ArrayList<Object> time_db;
    Context context;
    MoneyDB moneyDB;
    Cursor cursor;
    Money_Adapter money_adapter;

    public MoneyModel() {
        //empty
    }

    public MoneyModel(Context context) {

        this.context = context;
        id_db = new ArrayList<>();
        title_db = new ArrayList<>();
        amount_db = new ArrayList<>();
        type_db = new ArrayList<>();
        category_db = new ArrayList<>();
        date_db = new ArrayList<>();
        time_db = new ArrayList<>();
        moneyDB = new MoneyDB(context);

        store_list();

        money_adapter = new Money_Adapter(context, id_db, title_db, amount_db, type_db, category_db, date_db, time_db);

    }

    public void store_list() {
        cursor = moneyDB.readAllList();

            while (cursor.moveToNext()) {
                id_db.add(cursor.getString(0));
                title_db.add(cursor.getString(1));
                amount_db.add(cursor.getString(2));
                type_db.add(cursor.getString(3));
                category_db.add(cursor.getString(4));
                date_db.add(cursor.getString(5));
                time_db.add(cursor.getString(6));
            }
        Collections.reverse(id_db);
        Collections.reverse(title_db);
        Collections.reverse(amount_db);
        Collections.reverse(type_db);
        Collections.reverse(category_db);
        Collections.reverse(date_db);
        Collections.reverse(time_db);
    }

    public Money_Adapter getMoney_adapter() {
        return money_adapter;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public ArrayList<Object> getAmount() {
        return amount_db;
    }

    public ArrayList<Object> getCategory() {
        return category_db;
    }
}
