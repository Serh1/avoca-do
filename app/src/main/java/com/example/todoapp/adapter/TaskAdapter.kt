package com.example.todoapp.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.CurrentTask
import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskDatabase
import java.util.*


class TaskAdapter(
    private val dataset: List<Task>,
    private val onItemClicked: (Task) -> Unit
) :

RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private lateinit var database: TaskDatabase

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var taskTitle: TextView = view.findViewById(com.example.todoapp.R.id.task_title)
        var taskPriority: TextView = view.findViewById(com.example.todoapp.R.id.task_priority)
        var taskDate: TextView = view.findViewById(com.example.todoapp.R.id.task_date)
        var taskCategory: TextView = view.findViewById(com.example.todoapp.R.id.task_category)
        var layout: LinearLayout = view.findViewById(com.example.todoapp.R.id.mylayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task, parent, false)
        return TaskViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        when (dataset[position].priority?.lowercase(Locale.ROOT)) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#240046"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#FF6200EE"))
            "low" -> holder.layout.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }

        holder.taskTitle.text = dataset[position].taskTitle
        holder.taskPriority.text = dataset[position].priority
        holder.taskDate.text = dataset[position].date
        holder.taskCategory.text = dataset[position].category

        holder.itemView.setOnClickListener {
            CurrentTask.position = position
            onItemClicked(dataset[position])
        }
    }

    override fun getItemCount() = dataset.size
}