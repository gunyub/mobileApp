package com.example.todomemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText todoInput, memoInput;
    Button addButton, clearButton;
    Switch filterSwitch;
    RecyclerView recycler;

    List<TodoItem> todoList;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoInput = findViewById(R.id.todoInput);
        memoInput = findViewById(R.id.memoInput);
        addButton = findViewById(R.id.addButton);
        clearButton = findViewById(R.id.clearButton);
        filterSwitch = findViewById(R.id.filterSwitch);
        recycler = findViewById(R.id.todoRecycler);

        todoList = loadData();

        adapter = new TodoAdapter(todoList, this::saveData);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String todo = todoInput.getText().toString().trim();
            String memo = memoInput.getText().toString().trim();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

            if (!todo.isEmpty()) {
                todoList.add(new TodoItem(todo, memo, false, dateTime));
                adapter.notifyItemInserted(todoList.size() - 1);
                saveData();
                todoInput.setText("");
                memoInput.setText("");
            }
        });

        clearButton.setOnClickListener(v -> {
            todoList.clear();
            adapter.notifyDataSetChanged();
            saveData();
        });

        filterSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                List<TodoItem> filtered = new ArrayList<>();
                for (TodoItem item : todoList) {
                    if (item.isDone()) filtered.add(item);
                }
                adapter = new TodoAdapter(filtered, this::saveData);
            } else {
                adapter = new TodoAdapter(todoList, this::saveData);
            }
            recycler.setAdapter(adapter);
        });
    }

    private void saveData() {
        SharedPreferences prefs = getSharedPreferences("todo_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        StringBuilder sb = new StringBuilder();
        for (TodoItem item : todoList) {
            sb.append(item.getTodo()).append("##")
                    .append(item.getMemo()).append("##")
                    .append(item.isDone()).append("##")
                    .append(item.getDateTime()).append("$$");
        }

        editor.putString("todo_list", sb.toString());
        editor.apply();
    }

    private List<TodoItem> loadData() {
        SharedPreferences prefs = getSharedPreferences("todo_prefs", MODE_PRIVATE);
        String data = prefs.getString("todo_list", "");
        List<TodoItem> list = new ArrayList<>();

        if (!data.isEmpty()) {
            String[] items = data.split("\\$\\$");
            for (String item : items) {
                String[] parts = item.split("##");
                if (parts.length == 4) {
                    list.add(new TodoItem(parts[0], parts[1], Boolean.parseBoolean(parts[2]), parts[3]));
                }
            }
        }

        return list;
    }
}
