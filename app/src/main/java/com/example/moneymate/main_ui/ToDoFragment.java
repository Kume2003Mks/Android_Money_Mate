package com.example.moneymate.main_ui;

import android.content.Intent;
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

import com.example.moneymate.R;
import com.example.moneymate.databinding.FragmentTodoBinding;
import com.example.moneymate.intodomenu.Todo_Adapter;
import com.example.moneymate.intodomenu.add_todo;
import com.example.moneymate.intodomenu.todoDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ToDoFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Object> id_db;
    ArrayList<Object> title_db;
    ArrayList<Object> desc_db;
    todoDB DB_todo;
    Todo_Adapter todo_adapter;
    ImageView NoData_imv;
    TextView NoData_tw;
    private View view;
    private FragmentTodoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        this.view = root;

        NoData_imv = root.findViewById(R.id.Nodata_IV1);
        NoData_tw = root.findViewById(R.id.Nodata_TW1);

        DB_todo = new todoDB(getContext());
        id_db = new ArrayList<>();
        title_db = new ArrayList<>();
        desc_db = new ArrayList<>();

        recyclerView = root.findViewById(R.id.todo_recycler);
        todo_adapter = new Todo_Adapter(getContext(), id_db, title_db, desc_db);

        goto_add_todo();
        store_list();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(todo_adapter);

        return root;
    }

    public void store_list() {
        Cursor cursor = DB_todo.readAllList();
        if (cursor.getCount() == 0) {
            NoData_imv.setVisibility(view.VISIBLE);
            NoData_tw.setVisibility(view.VISIBLE);
        } else {
            NoData_imv.setVisibility(view.GONE);
            NoData_tw.setVisibility(view.GONE);
            while (cursor.moveToNext()) {
                id_db.add(cursor.getString(0));
                title_db.add(cursor.getString(1));
                desc_db.add(cursor.getString(2));
            }
        }
    }

    private void goto_add_todo() {
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floating_todo_go_page);
        floatingActionButton.setOnClickListener(view -> startActivity(new Intent(getActivity(), add_todo.class)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}