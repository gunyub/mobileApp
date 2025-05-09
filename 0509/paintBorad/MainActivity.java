package com.example.paintboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        paintView = findViewById(R.id.paintView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "검정");
        menu.add(0, 2, 0, "빨강");
        menu.add(0, 3, 0, "파랑");
        menu.add(0, 4, 0, "지우개");
        menu.add(0, 5, 0, "굵기 조절");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                paintView.setColor(Color.BLACK);
                return true;
            case 2:
                paintView.setColor(Color.RED);
                return true;
            case 3:
                paintView.setColor(Color.BLUE);
                return true;
            case 4:
                paintView.enableEraser();
                return true;
            case 5:
                showStrokeDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showStrokeDialog() {
        SeekBar seekBar = new SeekBar(this);
        seekBar.setMax(50);
        seekBar.setProgress(10);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("굵기 조절");
        builder.setView(seekBar);
        builder.setPositiveButton("확인", (dialog, which) ->
                paintView.setStrokeWidth(seekBar.getProgress()));
        builder.setNegativeButton("취소", null);
        builder.show();
    }
}
