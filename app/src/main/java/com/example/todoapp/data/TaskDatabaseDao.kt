package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Task

@Dao
interface TaskDatabaseDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * from table_tasks WHERE taskId = :key")
    fun get(key: Long): Task?

    @Query("DELETE FROM table_tasks")
    fun clear()

//    @Query("SELECT * FROM table_tasks ORDER BY taskId DESC LIMIT 1")
//    fun getTask(): Task?

    @Query("SELECT * FROM table_tasks ORDER BY taskId DESC")
    fun getAllTasks(): LiveData<List<Task>>
}