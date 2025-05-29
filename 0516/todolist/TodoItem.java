package com.example.todomemo;

public class TodoItem {
    private String todo;
    private String memo;
    private boolean isDone;
    private String dateTime;

    public TodoItem(String todo, String memo, boolean isDone, String dateTime) {
        this.todo = todo;
        this.memo = memo;
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
