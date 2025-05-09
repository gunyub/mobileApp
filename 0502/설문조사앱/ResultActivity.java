package com.example.smartusesurvey;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    TextView resultText;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        barChart = findViewById(R.id.barChart);

        int[] answers = getIntent().getIntArrayExtra("answers");

        showSummary(answers);
        showBarChart(answers);
    }

    private void showSummary(int[] answers) {
        int usage = answers[0];
        String usageStr;
        switch (usage) {
            case 0: usageStr = "1시간 미만"; break;
            case 1: usageStr = "1~3시간"; break;
            case 2: usageStr = "3~5시간"; break;
            default: usageStr = "5시간 이상"; break;
        }

        String summary = "📱 당신의 스마트폰 평균 사용 시간은 " + usageStr + "입니다.\n\n" +
                "기상 직후 사용: " + (answers[2] <= 1 ? "예" : "아니오") + "\n" +
                "자기 전 사용: " + (answers[3] <= 1 ? "예" : "아니오") + "\n" +
                "하루 확인 횟수: " + (answers[7] == 3 ? "40회 이상" : "적당함") + "\n" +
                "스마트폰 없이 하루 가능?: " + (answers[8] <= 1 ? "불가능" : "가능") + "\n";

        resultText.setText(summary);
    }

    private void showBarChart(int[] answers) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < answers.length; i++) {
            entries.add(new BarEntry(i + 1, answers[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, "질문별 선택지 (0~3)");
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.invalidate(); // 그래프 갱신
    }
}
