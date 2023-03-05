package com.example.moneymate.intodomenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymate.R;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class Todo_Adapter extends RecyclerView.Adapter<Todo_Adapter.MyViewHolder_todo> {

    Context context;
    ArrayList id;
    ArrayList title;
    ArrayList desc;

    public Todo_Adapter(Context context, ArrayList id, ArrayList title, ArrayList desc) {
        this.context = context;
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    @Override
    public MyViewHolder_todo onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_todo, parent, false);

        return new MyViewHolder_todo(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder_todo holder, int position) {
        holder.title_todo_adapter.setText(String.valueOf(title.get(position)));
        holder.desc_todo_adapter.setText(String.valueOf(set_desc_lgn()) + desc.get(position));

        holder.todo_reV.setOnClickListener(view -> {
            Intent intent = new Intent(context,update_and_complete_todo.class);
            intent.putExtra("id_todo",String.valueOf(id.get(position)));
            intent.putExtra("title_todo",String.valueOf(title.get(position)));
            intent.putExtra("desc_todo",String.valueOf(desc.get(position)));
            context.startActivity(intent);
        });
    }

    public String set_desc_lgn() {
        String DescText = (String) context.getText(R.string.description);
        DescText += ": ";
        return DescText;
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder_todo extends RecyclerView.ViewHolder {
        TextView title_todo_adapter;
        TextView desc_todo_adapter;
        LinearLayout todo_reV;

        public MyViewHolder_todo(@NonNull View itemView) {
            super(itemView);
            title_todo_adapter = itemView.findViewById(R.id.todo_title_textview);
            desc_todo_adapter = itemView.findViewById(R.id.todo_desc_textview);
            todo_reV = itemView.findViewById(R.id.todo_click);

        }
    }

}
