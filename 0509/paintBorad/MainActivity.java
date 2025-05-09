package com.example.paintboard;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PaintView 연결
        paintView = findViewById(R.id.paintView);

        // 버튼과 시크바 연결
        Button blackBtn = findViewById(R.id.blackBtn);
        Button redBtn = findViewById(R.id.redBtn);
        Button blueBtn = findViewById(R.id.blueBtn);
        Button eraserBtn = findViewById(R.id.eraserBtn);
        SeekBar strokeSeekBar = findViewById(R.id.strokeSeekBar);

        // 색상 버튼 기능
        blackBtn.setOnClickListener(v -> paintView.setColor(Color.BLACK));
        redBtn.setOnClickListener(v -> paintView.setColor(Color.RED));
        blueBtn.setOnClickListener(v -> paintView.setColor(Color.BLUE));

        // 지우개 기능
        eraserBtn.setOnClickListener(v -> paintView.enableEraser());

        // 굵기 조절 SeekBar 기능
        strokeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                paintView.setStrokeWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
