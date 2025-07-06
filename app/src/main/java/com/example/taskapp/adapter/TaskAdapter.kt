package com.example.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.ItemTaskBinding
import com.example.taskapp.model.Task

class TaskAdapter(private var tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.task = tasks[position]
    }

    override fun getItemCount() = tasks.size

    fun submitList(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
