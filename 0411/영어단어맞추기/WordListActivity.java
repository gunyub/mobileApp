package com.example.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WordListActivity extends AppCompatActivity {

    ListView wordListView;
    Button btnBackFromList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list); // XML 파일 이름에 맞춰주세요

        wordListView = findViewById(R.id.wordListView);
        btnBackFromList = findViewById(R.id.btnBackFromList);

        // 샘플 단어 리스트 (하드코딩)
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("abandon - 버리다");
        wordList.add("benefit - 이익");
        wordList.add("challenge - 도전");
        wordList.add("develop - 개발하다");
        wordList.add("effect - 효과");

        // 리스트에 어댑터 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                wordList
        );
        wordListView.setAdapter(adapter);

        // 돌아가기 버튼 처리
        btnBackFromList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 메인화면으로 이동
            }
        });
    }
}
