package com.example.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WordDetailActivity extends AppCompatActivity {

    TextView tvWord, tvMeaning, tvExample;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail); // XML 파일 이름이 정확히 일치해야 함

        tvWord = findViewById(R.id.tvWord);
        tvMeaning = findViewById(R.id.tvMeaning);
        tvExample = findViewById(R.id.tvExample);
        btnBack = findViewById(R.id.btnBack);

        // 임시 데이터 (하드코딩)
        tvWord.setText("abandon");
        tvMeaning.setText("버리다, 포기하다");
        tvExample.setText("He abandoned the project halfway.");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 메인화면으로 돌아가기
            }
        });
    }
}
