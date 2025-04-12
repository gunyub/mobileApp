package com.example.layouteditor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private boolean isBlue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isBlue) {
                    imageView.setBackgroundColor(Color.TRANSPARENT); // 원래대로
                } else {
                    imageView.setBackgroundColor(Color.parseColor("#FFBB86FC")); // 보라 계열 배경색
                }

                isBlue = !isBlue;
            }
        });
    }
}
