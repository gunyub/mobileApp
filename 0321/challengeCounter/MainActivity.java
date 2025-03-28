package com.example.challengecounter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button buttonIncrement;
    private Button buttonDecrement;
    private int counter = 0;  // 카운터 변수 초기화

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // XML 레이아웃을 지정

        // XML에서 뷰를 참조
        textView = findViewById(R.id.textView);
        buttonIncrement = findViewById(R.id.button);
        buttonDecrement = findViewById(R.id.button2);

        // 버튼 클릭 리스너 설정 - 카운터 증가
        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;  // 카운터 증가
                updateTextView();  // 텍스트 업데이트
            }
        });

        // 버튼 클릭 리스너 설정 - 카운터 감소
        buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;  // 카운터 감소
                updateTextView();  // 텍스트 업데이트
            }
        });
    }

    // 카운터 값을 텍스트뷰에 업데이트하는 메소드
    private void updateTextView() {
        textView.setText("카운터 : " + counter);  // 텍스트 내용 업데이트
    }
}