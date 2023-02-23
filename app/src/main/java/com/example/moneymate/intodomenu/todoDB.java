package com.example.moneymate.intodomenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

public class todoDB extends SQLiteOpenHelper {

    private static final String ID = BaseColumns._ID;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String DATABASE_NAME = "todo_list.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "list_todo";
    Context context;

    public todoDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String TODO_DB_TABLE = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                TABLE, ID, TITLE, DESCRIPTION);
        database.execSQL(TODO_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        String TODO_DB_TABLE = "DROP TABLE IF EXISTS todo";
        database.execSQL(TODO_DB_TABLE);
        onCreate(database);
    }

    public void addTODO(String title, String desc) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(DESCRIPTION, desc);
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

    public void update_todo(String id, String title, String desc) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, title);
        contentValues.put(DESCRIPTION, desc);

        long result = sqLiteDatabase.update(TABLE, contentValues, "_ID=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Fail Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update Complete", Toast.LENGTH_SHORT).show();
        }

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

    public void del_all_data(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE);
        sqLiteDatabase.close();
    }
}
