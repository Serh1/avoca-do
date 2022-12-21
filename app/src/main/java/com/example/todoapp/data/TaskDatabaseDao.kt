package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDatabaseDao {
    @Query("SELECT * FROM table_tasks ORDER BY taskId DESC")
    fun getAllTasks(): MutableList<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertTaskItem(task: Task)

    @Update
     fun updateTaskItem(task: Task)

    @Delete
     fun deleteTaskItem(task: Task)

    @Query("SELECT * from table_tasks WHERE taskId = :key")
    fun get(key: Int): Task?

//    @Query("DELETE FROM table_tasks")
//    fun clear()

//    @Query("SELECT * FROM table_tasks ORDER BY taskId DESC LIMIT 1")
//    fun getTask(): Task?


}