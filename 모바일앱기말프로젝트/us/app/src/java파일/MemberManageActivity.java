package com.example.us;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.us.adapters.UserAdapter;
import com.example.us.databinding.ActivityMemberManageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import com.example.us.models.User;

public class MemberManageActivity extends AppCompatActivity {

    private ActivityMemberManageBinding binding;
    private FirebaseFirestore db;
    private String calendarId;
    private String ownerId;
    private UserAdapter adapter;
    private ArrayList<User> memberList;
    private ArrayList<String> memberIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        calendarId = getIntent().getStringExtra("calendarId");
        ownerId = getIntent().getStringExtra("ownerId");
        memberList = new ArrayList<>();
        memberIds = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(memberList, user -> removeMember(user));
        binding.recyclerView.setAdapter(adapter);

        loadMembers();

        binding.btnAddMember.setOnClickListener(v -> {
            String email = binding.etMemberEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            db.collection("users").whereEqualTo("email", email).get().addOnSuccessListener(snapshot -> {
                if (!snapshot.isEmpty()) {
                    for (QueryDocumentSnapshot doc : snapshot) {
                        String userId = doc.getId();
                        if (!memberIds.contains(userId)) {
                            memberIds.add(userId);
                            db.collection("calendars").document(calendarId)
                                    .update("memberIds", memberIds)
                                    .addOnSuccessListener(unused -> {
                                        Toast.makeText(this, "구성원 추가됨", Toast.LENGTH_SHORT).show();
                                        loadMembers();
                                    });
                        } else {
                            Toast.makeText(this, "이미 구성원입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "해당 이메일의 사용자를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void loadMembers() {
        db.collection("calendars").document(calendarId).get().addOnSuccessListener(document -> {
            memberIds = (ArrayList<String>) document.get("memberIds");
            memberList.clear();
            if (memberIds == null) return;

            for (String id : memberIds) {
                db.collection("users").document(id).get().addOnSuccessListener(userDoc -> {
                    if (userDoc.exists()) {
                        User user = userDoc.toObject(User.class);
                        user.setId(id);
                        memberList.add(user);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void removeMember(User user) {
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (!currentUserId.equals(ownerId)) {
            Toast.makeText(this, "구성원 삭제 권한이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (user.getId().equals(ownerId)) {
            Toast.makeText(this, "소유자는 삭제할 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        memberIds.remove(user.getId());
        db.collection("calendars").document(calendarId)
                .update("memberIds", memberIds)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    loadMembers();
                });
    }
}
