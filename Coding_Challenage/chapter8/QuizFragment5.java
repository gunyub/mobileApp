package com.example.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.quizapp.QuizActivity;
import com.example.quizapp.R;

public class QuizFragment5 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        TextView question = view.findViewById(R.id.textQuestion);
        RadioButton option1 = view.findViewById(R.id.option1);
        RadioButton option2 = view.findViewById(R.id.option2);
        RadioButton option3 = view.findViewById(R.id.option3);
        Button btnNext = view.findViewById(R.id.btnNext);

        question.setText("1. 사과는 어떤 색이 아닌가요?");
        option1.setText("빨강");
        option2.setText("노랑");
        option3.setText("파랑"); // 정답

        btnNext.setOnClickListener(v -> {
            ((QuizActivity) requireActivity()).showNextQuestion();
        });

        return view;
    }
}
