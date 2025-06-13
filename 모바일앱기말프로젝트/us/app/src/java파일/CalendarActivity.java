package com.example.us;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.us.adapters.EventAdapter;
import com.example.us.databinding.ActivityCalendarBinding;
import com.example.us.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity {

    private ActivityCalendarBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ArrayList<Event> eventList;
    private EventAdapter adapter;
    private long selectedDate;
    private String calendarId;
    private String ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        eventList = new ArrayList<>();

        calendarId = getIntent().getStringExtra("calendarId");
        ownerId = getIntent().getStringExtra("ownerId");

        binding.tvCalendarName.setText(getIntent().getStringExtra("calendarName"));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(eventList, this::deleteEvent);
        binding.recyclerView.setAdapter(adapter);

        // 초기 날짜 설정 (오늘)
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        selectedDate = today.getTimeInMillis();
        loadEvents();

        binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, dayOfMonth, 0, 0, 0);
            cal.set(Calendar.MILLISECOND, 0);
            selectedDate = cal.getTimeInMillis();
            loadEvents();
        });

        binding.btnAddEvent.setOnClickListener(v -> showAddEventDialog());

        // 구성원 관리 버튼은 owner만 볼 수 있음
        if (mAuth.getCurrentUser().getUid().equals(ownerId)) {
            binding.btnManageMembers.setVisibility(View.VISIBLE);
            binding.btnManageMembers.setOnClickListener(v -> {
                Intent intent = new Intent(this, MemberManageActivity.class);
                intent.putExtra("calendarId", calendarId);
                intent.putExtra("ownerId", ownerId);
                startActivity(intent);
            });
        } else {
            binding.btnManageMembers.setVisibility(View.GONE);
        }
    }

    private void loadEvents() {
        db.collection("events")
                .whereEqualTo("calendarId", calendarId)
                .whereEqualTo("timestamp", selectedDate)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    eventList.clear();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Event event = doc.toObject(Event.class);
                        event.setId(doc.getId());
                        eventList.add(event);
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    private void showAddEventDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("일정 추가");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleInput = new EditText(this);
        titleInput.setHint("제목");
        titleInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(titleInput);

        final EditText descInput = new EditText(this);
        descInput.setHint("설명");
        descInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(descInput);

        builder.setView(layout);

        builder.setPositiveButton("추가", (dialog, which) -> {
            String title = titleInput.getText().toString().trim();
            String desc = descInput.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "제목은 필수입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> event = new HashMap<>();
            event.put("title", title);
            event.put("description", desc);
            event.put("calendarId", calendarId);
            event.put("timestamp", selectedDate); // 자정으로 고정된 timestamp 사용

            db.collection("events").add(event).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                loadEvents();
            });
        });

        builder.setNegativeButton("취소", null);
        builder.show();
    }

    private void deleteEvent(Event event) {
        db.collection("events").document(event.getId()).delete().addOnSuccessListener(unused -> {
            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
            loadEvents();
        });
    }
}
