package com.example.todomemo;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<TodoItem> todoList;
    private Runnable onDataChanged;
    private Context context;

    public TodoAdapter(List<TodoItem> todoList, Runnable onDataChanged) {
        this.todoList = todoList;
        this.onDataChanged = onDataChanged;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        TodoItem item = todoList.get(position);
        holder.todoText.setText(item.getTodo());
        holder.memoText.setText(item.getMemo());
        holder.dateTimeText.setText(item.getDateTime());
        holder.doneCheckBox.setChecked(item.isDone());

        holder.doneCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setDone(isChecked);
            onDataChanged.run();
        });

        holder.deleteButton.setOnClickListener(v -> {
            todoList.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            onDataChanged.run();
        });

        holder.itemView.setOnClickListener(v -> showEditDialog(holder.getAdapterPosition()));
    }

    private void showEditDialog(int position) {
        TodoItem item = todoList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("항목 수정");

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText todoInput = new EditText(context);
        todoInput.setHint("할 일");
        todoInput.setInputType(InputType.TYPE_CLASS_TEXT);
        todoInput.setText(item.getTodo());
        layout.addView(todoInput);

        EditText memoInput = new EditText(context);
        memoInput.setHint("메모");
        memoInput.setInputType(InputType.TYPE_CLASS_TEXT);
        memoInput.setText(item.getMemo());
        layout.addView(memoInput);

        builder.setView(layout);

        builder.setPositiveButton("저장", (dialog, which) -> {
            item.setTodo(todoInput.getText().toString());
            item.setMemo(memoInput.getText().toString());
            item.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date()));
            notifyItemChanged(position);
            onDataChanged.run();
        });

        builder.setNegativeButton("취소", null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView todoText, memoText, dateTimeText;
        Button deleteButton;
        CheckBox doneCheckBox;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoText = itemView.findViewById(R.id.todoText);
            memoText = itemView.findViewById(R.id.memoText);
            dateTimeText = itemView.findViewById(R.id.dateTimeText);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            doneCheckBox = itemView.findViewById(R.id.doneCheckBox);
        }
    }
}
