package com.example.quizapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.quizapp.fragment.*;

public class QuizActivity extends AppCompatActivity {

    private int currentIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        showQuestion(currentIndex);
    }

    public void showNextQuestion() {
        currentIndex++;
        if (currentIndex <= 5) {
            showQuestion(currentIndex);
        } else {
            finish(); // 또는 결과 화면으로 이동
        }
    }

    private void showQuestion(int index) {
        Fragment fragment = null;
        switch (index) {
            case 1: fragment = new QuizFragment1(); break;
            case 2: fragment = new QuizFragment2(); break;
            case 3: fragment = new QuizFragment3(); break;
            case 4: fragment = new QuizFragment4(); break;
            case 5: fragment = new QuizFragment5(); break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
