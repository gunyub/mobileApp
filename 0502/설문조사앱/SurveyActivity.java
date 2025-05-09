package com.example.smartusesurvey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SurveyActivity extends AppCompatActivity {

    TextView questionText;
    RadioGroup optionGroup;
    RadioButton option1, option2, option3, option4;
    Button nextButton;

    int currentQuestionIndex = 0;
    int[] answers = new int[10];

    String[] questions = {
            "1. 하루 스마트폰 사용 시간은?",
            "2. 가장 자주 사용하는 앱은?",
            "3. 아침에 일어나자마자 스마트폰을 확인하나요?",
            "4. 자기 전까지 스마트폰을 사용하나요?",
            "5. SNS 사용 시간은?",
            "6. 스마트폰으로 가장 많이 하는 활동은?",
            "7. 주말에는 얼마나 더 사용하나요?",
            "8. 하루에 스마트폰을 몇 번 확인하나요?",
            "9. 스마트폰 없이 하루도 가능합니까?",
            "10. 스마트폰 사용에 만족하십니까?"
    };

    String[][] options = {
            {"1시간 미만", "1~3시간", "3~5시간", "5시간 이상"},
            {"유튜브", "인스타그램", "카카오톡", "기타"},
            {"항상 그렇다", "가끔 그렇다", "거의 없다", "절대 안 본다"},
            {"항상 사용한다", "가끔 사용한다", "거의 사용 안 함", "절대 안 씀"},
            {"30분 미만", "30분~1시간", "1~2시간", "2시간 이상"},
            {"영상 시청", "게임", "SNS", "기타"},
            {"변화 없다", "1시간 더", "2시간 더", "3시간 이상"},
            {"10번 이하", "10~20번", "20~40번", "40번 이상"},
            {"절대 불가", "조금 불편", "하루쯤 괜찮음", "충분히 가능"},
            {"매우 만족", "보통", "불만족", "매우 불만족"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        questionText = findViewById(R.id.questionText);
        optionGroup = findViewById(R.id.optionGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextButton);

        loadQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = optionGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(SurveyActivity.this, "항목을 선택하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedOption = optionGroup.indexOfChild(findViewById(selectedId));
                answers[currentQuestionIndex] = selectedOption;
                currentQuestionIndex++;

                if (currentQuestionIndex < questions.length) {
                    loadQuestion();
                } else {
                    Intent intent = new Intent(SurveyActivity.this, ResultActivity.class);
                    intent.putExtra("answers", answers);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);
        optionGroup.clearCheck();
    }
}
