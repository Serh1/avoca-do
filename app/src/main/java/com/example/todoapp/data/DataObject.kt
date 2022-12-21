package com.example.todoapp.data

import android.content.SharedPreferences

object DataObject : java.io.Serializable {
    var listData = mutableListOf<Task>()
    var currentData : Int = 0
    lateinit var sharedPreferences: SharedPreferences

    fun setData(taskId:Int,title: String, priority: String,date: String,category: String) {
        listData.add(0, Task(taskId,title, priority,date,category))
    }

    fun getAllData(): List<Task> {
        return listData
    }

    fun deleteAll(){
        listData.clear()
    }

    fun getData(pos:Int): Task {
        return listData[pos]
    }

    fun deleteData(pos:Int){
        listData.removeAt(pos)
    }
//
//    fun saveData(){
//        val sharedPreferences = getSharedPreferences(SHARED_PREFS, AppCompatActivity.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//
//    }
//    TODO: Add the other elements
    fun updateData(pos:Int,newTask: Task)
    {
        listData[pos].taskTitle=newTask.taskTitle
        listData[pos].priority=newTask.priority
        listData[pos].category=newTask.category
        listData[pos].date=newTask.date
    }
}