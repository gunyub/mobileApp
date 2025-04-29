package com.example.aiconmakebygpt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private TextView instructionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_layout);
        instructionText = findViewById(R.id.instruction_text);

        // 컨텍스트 메뉴를 등록할 뷰 지정
        registerForContextMenu(mainLayout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // 메뉴 인플레이터를 통해 XML로 정의된 메뉴 리소스를 인플레이트
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_colors, menu);

        // 메뉴 타이틀 설정
        menu.setHeaderTitle("배경색 선택");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // 선택된 메뉴 아이템 처리
        int itemId = item.getItemId();

        if (itemId == R.id.menu_red) {
            changeBackgroundColor(Color.RED, "빨간색");
            return true;
        } else if (itemId == R.id.menu_green) {
            changeBackgroundColor(Color.GREEN, "녹색");
            return true;
        } else if (itemId == R.id.menu_blue) {
            changeBackgroundColor(Color.BLUE, "파란색");
            return true;
        } else if (itemId == R.id.menu_yellow) {
            changeBackgroundColor(Color.YELLOW, "노란색");
            return true;
        } else if (itemId == R.id.menu_purple) {
            changeBackgroundColor(Color.MAGENTA, "보라색");
            return true;
        } else if (itemId == R.id.menu_white) {
            changeBackgroundColor(Color.WHITE, "흰색");
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void changeBackgroundColor(int color, String colorName) {
        // 배경색 변경
        mainLayout.setBackgroundColor(color);

        // 배경색에 따라 텍스트 색상 조정 (가독성을 위해)
        if (color == Color.WHITE || color == Color.YELLOW || color == Color.GREEN) {
            instructionText.setTextColor(Color.BLACK);
        } else {
            instructionText.setTextColor(Color.WHITE);
        }

        // 토스트 메시지 표시
        Toast.makeText(this, colorName + "으로 배경색이 변경되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
