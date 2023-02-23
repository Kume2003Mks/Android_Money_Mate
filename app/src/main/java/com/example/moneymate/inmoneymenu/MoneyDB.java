package com.example.moneymate.inmoneymenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MoneyDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "money_list.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "list_money";
    private static final String ID = BaseColumns._ID;
    private static final String TITLE = "title";
    private static final String AMOUNT = "amount";
    private static final String TYPE = "type";
    private static final String CATEGORY = "category";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private Context context;

    public MoneyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String MONEY_DB_TABLE = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s TEXT, %s REAL, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE, ID, TITLE, AMOUNT, TYPE, CATEGORY, DATE, TIME);
        sqLiteDatabase.execSQL(MONEY_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        String TODO_DB_TABLE = "DROP TABLE IF EXISTS money";
        database.execSQL(TODO_DB_TABLE);
        onCreate(database);
    }

    public void addIE(String title, double amount, String type, String category, String date, String time) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(AMOUNT, amount);
        contentValues.put(TYPE, type);
        contentValues.put(CATEGORY, category);
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);

        long result = sqLiteDatabase.insert(TABLE, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Complete", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
    }

    public Cursor readAllList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public List<Double> getAmount() {
        List<Double> am = new ArrayList<Double>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query
                (TABLE, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            am.add(cursor.getDouble(2));
            cursor.moveToNext();
        }
        db.close();
        return am;
    }

    public void del_all_data(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE);
        sqLiteDatabase.close();
    }

    public void del_select_data(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE, "_ID=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Double> get_General_data() {
        List<Double> list = new ArrayList<Double>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CATEGORY + "='General'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            list.add(cursor.getDouble(2));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    public List<Double> get_Food_data() {
        List<Double> list = new ArrayList<Double>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CATEGORY + "='Food'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            list.add(cursor.getDouble(2));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    public List<Double> get_Shopping_data() {
        List<Double> list = new ArrayList<Double>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CATEGORY + "='Shopping'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            list.add(cursor.getDouble(2));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    public List<Double> get_Fare_data() {
        List<Double> list = new ArrayList<Double>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CATEGORY + "='Fare'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            list.add(cursor.getDouble(2));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    public List<Double> get_Utilities_data() {
        List<Double> list = new ArrayList<Double>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CATEGORY + "='Utilities'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            list.add(cursor.getDouble(2));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }
}
