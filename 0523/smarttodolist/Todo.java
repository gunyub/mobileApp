package com.example.smarttodocalendar;

public class Todo {
    public int id;
    public String title;
    public long timeMillis;

    public Todo(int id, String title, long timeMillis) {
        this.id = id;
        this.title = title;
        this.timeMillis = timeMillis;
    }
}