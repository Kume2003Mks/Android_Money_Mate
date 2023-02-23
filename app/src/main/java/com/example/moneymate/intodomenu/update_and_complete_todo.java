package com.example.moneymate.intodomenu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymate.MainActivity;
import com.example.moneymate.R;

public class update_and_complete_todo extends AppCompatActivity {

    private EditText update_title;
    private EditText update_desc;
    String id;
    String desc;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_complete_todo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TODO");

        update_title = findViewById(R.id.title_todo_update);
        update_desc = findViewById(R.id.desc_todo_update);

        getData();
    }
    private void getData(){
        if(getIntent().hasExtra("id_todo") && getIntent().hasExtra("title_todo") && getIntent().hasExtra("desc_todo")){
            id = getIntent().getStringExtra("id_todo");
            title = getIntent().getStringExtra("title_todo");
            desc = getIntent().getStringExtra("desc_todo");

            update_title.setText(title);
            update_desc.setText(desc);
        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    public void update_todo(View view) {
        String title = update_title.getText().toString();
        String desc = update_desc.getText().toString();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getApplicationContext(), "Enter Title", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(desc)) {
            Toast.makeText(getApplicationContext(), "Enter Description", Toast.LENGTH_SHORT).show();
            return;
        }

        todoDB db = new todoDB(getApplicationContext());
        db.update_todo(id,title,desc);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void complete_todo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Complete : " + title);
        builder.setPositiveButton("yes", (dialogInterface, i) -> {
            todoDB db = new todoDB(getApplicationContext());
            db.del_select_data(id);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
}