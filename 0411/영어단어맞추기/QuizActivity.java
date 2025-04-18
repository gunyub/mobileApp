package com.example.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuizQuestion, tvQuizResult;
    Button btnChoice1, btnChoice2, btnChoice3, btnChoice4, btnBackFromQuiz;

    // 예시 문제
    String correctAnswer = "버리다";  // 정답

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); // XML 이름 맞춰주세요

        tvQuizQuestion = findViewById(R.id.tvQuizQuestion);
        tvQuizResult = findViewById(R.id.tvQuizResult);
        btnChoice1 = findViewById(R.id.btnChoice1);
        btnChoice2 = findViewById(R.id.btnChoice2);
        btnChoice3 = findViewById(R.id.btnChoice3);
        btnChoice4 = findViewById(R.id.btnChoice4);
        btnBackFromQuiz = findViewById(R.id.btnBackFromQuiz);

        // 문제 표시
        tvQuizQuestion.setText("Q. abandon의 뜻은?");

        // 보기 설정
        btnChoice1.setText("버리다");
        btnChoice2.setText("걷다");
        btnChoice3.setText("사랑하다");
        btnChoice4.setText("먹다");

        // 선택지 클릭 리스너 등록
        View.OnClickListener choiceListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clicked = (Button) view;
                String selected = clicked.getText().toString();

                if (selected.equals(correctAnswer)) {
                    tvQuizResult.setText("정답입니다! 🎉");
                    tvQuizResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    tvQuizResult.setText("오답입니다 😢 정답: " + correctAnswer);
                    tvQuizResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        };

        btnChoice1.setOnClickListener(choiceListener);
        btnChoice2.setOnClickListener(choiceListener);
        btnChoice3.setOnClickListener(choiceListener);
        btnChoice4.setOnClickListener(choiceListener);

        // 뒤로가기
        btnBackFromQuiz.setOnClickListener(v -> finish());
    }
}
