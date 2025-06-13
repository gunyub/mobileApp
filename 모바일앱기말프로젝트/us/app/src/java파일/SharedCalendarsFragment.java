package com.example.us;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.us.adapters.CalendarAdapter;
import com.example.us.databinding.FragmentSharedCalendarsBinding;
import com.example.us.models.CalendarModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SharedCalendarsFragment extends Fragment {

    private FragmentSharedCalendarsBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ArrayList<CalendarModel> calendarList;
    private CalendarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSharedCalendarsBinding.inflate(inflater, container, false);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        calendarList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CalendarAdapter(calendarList, calendar -> openCalendar(calendar));
        binding.recyclerView.setAdapter(adapter);

        binding.btnAddCalendar.setOnClickListener(v -> showAddCalendarDialog());

        loadCalendars();

        return binding.getRoot();
    }

    private void loadCalendars() {
        String uid = mAuth.getCurrentUser().getUid();
        db.collection("calendars")
                .whereArrayContains("memberIds", uid)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    calendarList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        CalendarModel model = doc.toObject(CalendarModel.class);
                        model.setId(doc.getId());
                        calendarList.add(model);
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    private void showAddCalendarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("공유 캘린더 추가");

        final EditText input = new EditText(getContext());
        input.setHint("캘린더 이름");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("생성", (dialog, which) -> {
            String name = input.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(getContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            String calendarId = UUID.randomUUID().toString();
            String ownerId = mAuth.getCurrentUser().getUid();

            Map<String, Object> calendar = new HashMap<>();
            calendar.put("name", name);
            calendar.put("ownerId", ownerId);
            calendar.put("memberIds", new ArrayList<String>() {{
                add(ownerId);
            }});

            db.collection("calendars").document(calendarId)
                    .set(calendar)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(getContext(), "캘린더 생성 완료", Toast.LENGTH_SHORT).show();
                        loadCalendars();
                    });
        });

        builder.setNegativeButton("취소", null);
        builder.show();
    }

    private void openCalendar(CalendarModel calendar) {
        Intent intent = new Intent(getContext(), CalendarActivity.class);
        intent.putExtra("calendarId", calendar.getId());
        intent.putExtra("calendarName", calendar.getName());
        intent.putExtra("ownerId", calendar.getOwnerId());
        startActivity(intent);
    }
}
