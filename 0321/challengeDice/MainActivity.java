package com.example.challengedice;  // 패키지 이름을 본인 앱에 맞게 수정하세요

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView diceImageView;  // 주사위 이미지 뷰
    private Button rollButton;  // 롤 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ImageView와 Button 초기화
        diceImageView = findViewById(R.id.imageView);
        rollButton = findViewById(R.id.button);

        // 버튼 클릭 시 주사위 회전 애니메이션 실행
        rollButton.setOnClickListener(v -> rollDice());
    }

    // 주사위 회전 애니메이션
    private void rollDice() {
        // 랜덤 각도 계산 (360도에서 720도 사이)
        Random random = new Random();
        int randomAngle = 360 + random.nextInt(361);  // 360 ~ 720 사이의 랜덤 각도 생성

        // 회전 애니메이션 설정
        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, randomAngle, // 0도에서 랜덤 각도까지 회전
                Animation.RELATIVE_TO_SELF, 0.5f, // 중심으로 회전
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(700); // 회전 시간 1초
        rotateAnimation.setRepeatCount(0);  // 반복하지 않음
        rotateAnimation.setFillAfter(true);  // 애니메이션 끝난 후 마지막 상태 유지

        // 애니메이션 시작
        diceImageView.startAnimation(rotateAnimation);
    }
}