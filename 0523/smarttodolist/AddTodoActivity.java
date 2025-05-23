package com.example.smarttodocalendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTodoActivity extends AppCompatActivity {

    private EditText editTitle;
    private Button btnDate, btnTime, btnSave;
    private Calendar calendar = Calendar.getInstance();
    private long selectedMillis = 0;
    private int todoId = -1; // 수정 모드일 경우 id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        editTitle = findViewById(R.id.editTitle);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnSave = findViewById(R.id.btnSave);

        // 수정 모드인지 확인
        Intent intent = getIntent();
        todoId = intent.getIntExtra("todo_id", -1);

        if (todoId != -1) {
            // 수정 모드: 기존 값 세팅
            String title = intent.getStringExtra("title");
            long timeMillis = intent.getLongExtra("timeMillis", 0);
            editTitle.setText(title);
            if (timeMillis != 0) {
                calendar.setTimeInMillis(timeMillis);
                selectedMillis = timeMillis;
                // 날짜/시간 버튼에 기존 값 표시
                btnDate.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(new Date(timeMillis)));
                btnTime.setText(new SimpleDateFormat("HH:mm", Locale.KOREA).format(new Date(timeMillis)));
            }
            btnSave.setText("수정하기");
        } else {
            // 신규 등록
            btnSave.setText("저장하기");
        }

        btnDate.setOnClickListener(v -> {
            DatePickerDialog dp = new DatePickerDialog(this,
                    (view, year, month, day) -> {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        btnDate.setText(year + "-" + (month + 1) + "-" + day);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dp.show();
        });

        btnTime.setOnClickListener(v -> {
            TimePickerDialog tp = new TimePickerDialog(this,
                    (view, hour, minute) -> {
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        btnTime.setText(hour + ":" + String.format("%02d", minute));
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false);
            tp.show();
        });

        btnSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString();
            selectedMillis = calendar.getTimeInMillis();
            if (title.isEmpty() || selectedMillis == 0) {
                Toast.makeText(this, "제목과 날짜/시간을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            TodoDBHelper dbHelper = new TodoDBHelper(this);

            if (todoId == -1) {
                // 신규 등록
                dbHelper.insertTodo(title, selectedMillis);
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                // 수정
                dbHelper.updateTodo(todoId, title, selectedMillis);
                Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
            AlarmHelper.setAlarm(this, title, selectedMillis); // 알람 30분 전 설정
            finish();
        });
    }
}
