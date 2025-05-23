package com.example.smarttodocalendar;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private TodoDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Android 13+ 알림 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
            }
        }

        dbHelper = new TodoDBHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTodos();

        // 추가 버튼
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddTodoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTodos();
    }

    private void loadTodos() {
        List<Todo> todos = dbHelper.getAllTodos();
        adapter = new TodoAdapter(todos);
        recyclerView.setAdapter(adapter);

        // 삭제/수정 기능 연결
        adapter.setOnItemClickListener(new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 클릭 시 수정 화면으로 이동
                Todo todo = adapter.getTodoAt(position);
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                intent.putExtra("todo_id", todo.id);
                intent.putExtra("title", todo.title);
                intent.putExtra("timeMillis", todo.timeMillis);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {
                // 롱클릭 시 삭제/수정 다이얼로그
                Todo todo = adapter.getTodoAt(position);
                showTodoOptionsDialog(todo);
            }
        });
    }

    private void showTodoOptionsDialog(Todo todo) {
        new AlertDialog.Builder(this)
                .setTitle("할 일")
                .setMessage("할 일을 삭제하거나 수정할 수 있습니다.")
                .setPositiveButton("삭제", (dialog, which) -> {
                    dbHelper.deleteTodo(todo.id);
                    loadTodos();
                    Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("수정", (dialog, which) -> {
                    Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                    intent.putExtra("todo_id", todo.id);
                    intent.putExtra("title", todo.title);
                    intent.putExtra("timeMillis", todo.timeMillis);
                    startActivity(intent);
                })
                .setNeutralButton("취소", null)
                .show();
    }
}
