package com.example.us.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.us.R;
import com.example.us.models.CalendarModel;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    public interface OnCalendarClickListener {
        void onClick(CalendarModel calendar);
    }

    private List<CalendarModel> calendarList;
    private OnCalendarClickListener listener;

    public CalendarAdapter(List<CalendarModel> calendarList, OnCalendarClickListener listener) {
        this.calendarList = calendarList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        CalendarModel model = calendarList.get(position);
        holder.tvName.setText(model.getName());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return calendarList.size();
    }

    static class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCalendarName);
        }
    }
}
