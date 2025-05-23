package com.example.smarttodocalendar;

import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private List<Todo> todoList;

    // 클릭 이벤트 인터페이스
    public interface OnItemClickListener {
        void onItemClick(int position);        // 클릭(수정)
        void onItemLongClick(int position);    // 롱클릭(삭제/수정)
    }

    private OnItemClickListener listener;

    // 리스너 등록 함수
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TodoAdapter(List<Todo> todos) {
        todoList = todos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.textTitle.setText(todo.title);
        holder.textTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(new Date(todo.timeMillis)));
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    // 외부에서 Todo 객체를 쉽게 가져오도록 함수 추가
    public Todo getTodoAt(int position) {
        return todoList.get(position);
    }

    // (추가) 데이터 변경 시 갱신할 수 있도록 메서드 추가
    public void setTodoList(List<Todo> todos) {
        this.todoList = todos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textTime;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textTime = itemView.findViewById(R.id.textTime);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(pos);
                }
            });

            itemView.setOnLongClickListener(v -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(pos);
                }
                return true;
            });
        }
    }
}
