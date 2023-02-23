package com.example.moneymate.intodomenu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymate.MainActivity;
import com.example.moneymate.R;

public class add_todo extends AppCompatActivity {

    private EditText eTitle;
    private EditText eDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TODO");

        eTitle = (EditText) findViewById(R.id.title_todo);
        eDescription = (EditText) findViewById(R.id.desc_todo);

    }

    public void save_todo(View view) {
        String title, description;

        todoDB tdDB = new todoDB(getApplicationContext());

        title = eTitle.getText().toString();
        description = eDescription.getText().toString();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getApplicationContext(), "Enter Title", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(description)) {
            Toast.makeText(getApplicationContext(), "Enter Description", Toast.LENGTH_SHORT).show();
            return;
        }

        tdDB.addTODO(title, description);

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}