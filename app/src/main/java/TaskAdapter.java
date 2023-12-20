package com.example.todoapp;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private  ArrayList<Task> tasks;
    private OnClickListener onClickListener;
    public TaskAdapter(ArrayList<Task> tasks,OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.tasks = tasks;

    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate,tvDay,tvTime,tvTaskText;
        CheckBox checkBox;

        public TaskViewHolder(@NonNull View itemView,OnClickListener onClickListener) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDay  = itemView.findViewById(R.id.tvDay);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTaskText = itemView.findViewById(R.id.tvTaskText);
            checkBox   = itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(v-> onClickListener.onItemClick(getAdapterPosition()));
            checkBox.setOnCheckedChangeListener((v,checked)-> onClickListener.onChecked(getAdapterPosition(),checked));
        }
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row_layout,parent,false);
       //TaskViewHolder taskViewHolder = new TaskViewHolder(itemView);
        return new TaskViewHolder(itemView,onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.tvTaskText.setText(task.getText());
        holder.tvDate.setText(DateFormat.format("dd", task.getCreated()));
        holder.tvDay.setText(DateFormat.format("E", task.getCreated()));
        holder.tvTime.setText(DateFormat.format("hh:mm A", task.getCreated()));
        holder.checkBox.setChecked(task.isCompleted());

        /*
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "position: " + position, Toast.LENGTH_SHORT).show();
        });

        holder.checkBox.setOnCheckedChangeListener((v, checked) -> {
            Toast.makeText(v.getContext(), "checked : " + checked, Toast.LENGTH_SHORT).show();
        });
         */
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
