package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tasks")
data class Task(
//    @PrimaryKey(autoGenerate = true)
//    var taskId: Long = 0L,

    @ColumnInfo(name = "title")
    var taskTitle: String?,

    @ColumnInfo(name = "priority")
    var priority: String?,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "category")
    var category: String?,
)