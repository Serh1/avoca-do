package com.example.todoapp.data

import com.example.todoapp.model.Task

object DataObject : java.io.Serializable {
    var listData = mutableListOf<Task>()
    var currentData : Int = 0

    fun setData(title: String, priority: String,date: String,category: String) {
        listData.add(Task(title, priority,date,category))
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
//    TODO: Add the other elements
    fun updateData(pos:Int,newTask:Task)
    {
        listData[pos].taskTitle=newTask.taskTitle
        listData[pos].priority=newTask.priority
        listData[pos].category=newTask.category
        listData[pos].date=newTask.date
    }
}