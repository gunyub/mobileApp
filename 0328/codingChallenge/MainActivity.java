package com.example.calculatorbygpt;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1Edit, num2Edit;
    TextView resultText;
    Button btnAdd, btnSub, btnMul, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 컴포넌트 연결
        num1Edit = findViewById(R.id.num1);
        num2Edit = findViewById(R.id.num2);
        resultText = findViewById(R.id.resultText);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        // 각 버튼의 클릭 리스너 설정
        btnAdd.setOnClickListener(view -> calculate('+'));
        btnSub.setOnClickListener(view -> calculate('-'));
        btnMul.setOnClickListener(view -> calculate('*'));
        btnDiv.setOnClickListener(view -> calculate('/'));
    }

    private void calculate(char operator) {
        String num1Str = num1Edit.getText().toString();
        String num2Str = num2Edit.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            resultText.setText("숫자를 모두 입력하세요.");
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    resultText.setText("0으로 나눌 수 없습니다.");
                    return;
                }
                result = num1 / num2;
                break;
        }

        resultText.setText("결과 : " + result);
    }
}
