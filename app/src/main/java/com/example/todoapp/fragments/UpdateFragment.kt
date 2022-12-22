package com.example.todoapp.fragments

import android.accounts.AuthenticatorDescription
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todoapp.R
import com.example.todoapp.data.CurrentTask
import com.example.todoapp.list.Category
import com.example.todoapp.list.Priority
import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UpdateFragment : Fragment() {
    private lateinit var update: Button
    private lateinit var delete: Button
    private lateinit var updateTitle: EditText
    private lateinit var updateDescription: EditText
    private lateinit var updatePriority: EditText
    private lateinit var pickDate: Button
    private lateinit var dateView: TextView
    private lateinit var categorySpinner: Spinner
    private lateinit var prioritySpinner: Spinner

    private lateinit var database: TaskDatabase


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        updateTitle = view.findViewById(R.id.update_title)
        updateDescription = view.findViewById(R.id.update_description)
        prioritySpinner = view.findViewById(R.id.update_priority)
        categorySpinner = view.findViewById(R.id.update_category)
        dateView = view.findViewById(R.id.date)
        pickDate = view.findViewById(R.id.update_date)
        delete = view.findViewById(R.id.delete_button)
        update = view.findViewById(R.id.update_button)


        database = Room.databaseBuilder(
            requireContext(), TaskDatabase::class.java, "To_Do"
        ).allowMainThreadQueries().build()

        val task = database.taskDatabaseDao.get(CurrentTask.position)
        Log.d("Task Selected", task.toString())
        Log.d("Task Selected", CurrentTask.position.toString())

        if (task != null) {
            updateTitle.setText(task.taskTitle.toString())
            updateDescription.setText(task.description.toString())
            dateView.text = task.date
            setPriority(task)
            setCategory(task)
        }

        val myCalendar = Calendar.getInstance()

        pickDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                getDatePicker(myCalendar),
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        delete.setOnClickListener {
            GlobalScope.launch {
                if (task != null) {
                    database.taskDatabaseDao.deleteTaskItem(task)
                }
            }
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }

        update.setOnClickListener {
            if (updateTitle.text.toString().trim { it <= ' ' }.isNotEmpty()) {
                val title = updateTitle.text.toString()
                val description = updateDescription.text.toString()
                val priority = prioritySpinner.selectedItem.toString()
                val date = dateView.text.toString()
                val category = categorySpinner.selectedItem.toString()
                val newTask = Task(CurrentTask.position, title,description, priority, date, category)
                GlobalScope.launch {
                    database.taskDatabaseDao.updateTaskItem(
                        newTask
                    )
                }
                findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
            }
        }
        return view
    }

    private fun setPriority(task: Task?) {
        val listOfPriority = getListOf("Priority")
        prioritySpinner.adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listOfPriority) }
        val pos = task?.let { getPositionPriority(it) }
        if (pos != null) {
            categorySpinner.setSelection(pos)
        }
    }

    private fun getPositionPriority(task: Task):Int{
        return when (task.priority) {
            "LOW" -> 0
            "MEDIUM" -> 1
            "HIGH" -> 2
            else -> 0
        }
    }

    private fun setCategory(task: Task?) {
        if (task != null) {
            val listOfCategory = getListOf("Category")
            categorySpinner.adapter =
                activity?.let {
                    ArrayAdapter(it, android.R.layout.simple_spinner_item, listOfCategory)
                }
            val pos = getPositionCategory(task)
            categorySpinner.setSelection(pos)
        }
    }

    private fun getPositionCategory(task: Task):Int{
        return when (task.category) {
            "HOME" -> 0
            "JOB" -> 1
            "HOBBY" -> 2
            else -> 0
        }
    }


    private fun getDatePicker(calendar: Calendar): DatePickerDialog.OnDateSetListener {
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(calendar)
        }
        return datePicker
    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        dateView.text = sdf.format(myCalendar.time)
    }

    private fun getListOf(type: String): ArrayList<String> {
        val list = ArrayList<String>()
        if (type == "Category") {
            for (category in Category.values()) {
                list.add(category.toString())
            }
        } else {
            for (priority in Priority.values()) {
                list.add(priority.toString())
            }
        }
        return list
    }

}