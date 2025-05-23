package com.example.smarttodocalendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDBHelper extends SQLiteOpenHelper {
    public TodoDBHelper(Context context) {
        super(context, "todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS todo (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, timeMillis LONG)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo");
        onCreate(db);
    }

    // 할 일 추가
    public void insertTodo(String title, long timeMillis) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO todo (title, timeMillis) VALUES (?, ?)", new Object[]{title, timeMillis});
        db.close();
    }

    // 모든 할 일 가져오기
    public List<Todo> getAllTodos() {
        SQLiteDatabase db = getReadableDatabase();
        List<Todo> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM todo ORDER BY timeMillis ASC", null);
        while (c.moveToNext()) {
            list.add(new Todo(
                    c.getInt(c.getColumnIndexOrThrow("id")),
                    c.getString(c.getColumnIndexOrThrow("title")),
                    c.getLong(c.getColumnIndexOrThrow("timeMillis"))
            ));
        }
        c.close();
        db.close();
        return list;
    }

    // 할 일 삭제
    public void deleteTodo(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM todo WHERE id=?", new Object[]{id});
        db.close();
    }

    // 할 일 수정
    public void updateTodo(int id, String title, long timeMillis) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE todo SET title=?, timeMillis=? WHERE id=?", new Object[]{title, timeMillis, id});
        db.close();
    }
}
