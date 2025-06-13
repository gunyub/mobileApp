package com.example.us;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.us.adapters.EventAdapter;
import com.example.us.databinding.FragmentMyCalendarsBinding;
import com.example.us.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MyCalendarsFragment extends Fragment {

    private FragmentMyCalendarsBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ArrayList<Event> eventList;
    private EventAdapter adapter;
    private long selectedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyCalendarsBinding.inflate(inflater, container, false);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        eventList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

        return binding.getRoot();
    }

    private void loadEvents() {
        db.collection("events")
                .whereEqualTo("calendarId", "personal")
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("일정 추가");

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleInput = new EditText(getContext());
        titleInput.setHint("제목");
        titleInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(titleInput);

        final EditText descInput = new EditText(getContext());
        descInput.setHint("설명");
        descInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(descInput);

        builder.setView(layout);

        builder.setPositiveButton("추가", (dialog, which) -> {
            String title = titleInput.getText().toString().trim();
            String desc = descInput.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(getContext(), "제목은 필수입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> event = new HashMap<>();
            event.put("title", title);
            event.put("description", desc);
            event.put("calendarId", "personal");
            event.put("timestamp", selectedDate); // 항상 자정 기준으로 저장

            db.collection("events").add(event).addOnSuccessListener(documentReference -> {
                Toast.makeText(getContext(), "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                loadEvents();
            });
        });

        builder.setNegativeButton("취소", null);
        builder.show();
    }

    private void deleteEvent(Event event) {
        db.collection("events").document(event.getId()).delete().addOnSuccessListener(unused -> {
            Toast.makeText(getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
            loadEvents();
        });
    }
}
