package com.example.smarttodocalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.*;
import java.util.Calendar;

public class AlarmHelper {
    public static void setAlarm(Context context, String title, long millis) {
        long alarmMillis = millis - (1 * 60 * 1000); // 30분 전
        if (alarmMillis < System.currentTimeMillis()) return; // 과거는 예약 X

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("title", title);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, (int) millis, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setExact(AlarmManager.RTC_WAKEUP, alarmMillis, pIntent);
    }
}