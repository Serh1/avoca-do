package com.example.todoapp.model

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _nrOfTasks = 0
    var nrOfTasks: Int  = 0
        get() = _nrOfTasks

    private var taskList: MutableList<Task> = mutableListOf()

    private fun getAllTasks() {


    }

    private fun addTask(task: Task) {

    }



}