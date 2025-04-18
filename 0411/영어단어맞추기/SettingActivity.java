package com.example.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    RadioGroup radioDifficulty;
    RadioButton radioEasy, radioNormal, radioHard;
    Switch switchNotification;
    Button btnBackFromSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting); // XML 이름과 일치하게!

        radioDifficulty = findViewById(R.id.radioDifficulty);
        radioEasy = findViewById(R.id.radioEasy);
        radioNormal = findViewById(R.id.radioNormal);
        radioHard = findViewById(R.id.radioHard);
        switchNotification = findViewById(R.id.switchNotification);
        btnBackFromSetting = findViewById(R.id.btnBackFromSetting);

        // 난이도 선택 처리
        radioDifficulty.setOnCheckedChangeListener((group, checkedId) -> {
            String selected = "";
            if (checkedId == R.id.radioEasy) selected = "쉬움";
            else if (checkedId == R.id.radioNormal) selected = "보통";
            else if (checkedId == R.id.radioHard) selected = "어려움";

            Toast.makeText(this, "난이도: " + selected, Toast.LENGTH_SHORT).show();
        });

        // 알림 스위치 처리
        switchNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "알림 ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "알림 OFF", Toast.LENGTH_SHORT).show();
            }
        });

        // 뒤로가기 버튼
        btnBackFromSetting.setOnClickListener(v -> finish());
    }
}
