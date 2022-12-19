package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.DataObject
import com.example.todoapp.list.Category
import com.example.todoapp.list.Priority
import com.example.todoapp.model.Task
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UpdateFragment : Fragment() {
    private lateinit var update: Button
    private lateinit var delete: Button
    private lateinit var updateTitle: EditText
    private lateinit var updatePriority: EditText
    private lateinit var pickDate: Button
    private lateinit var dateView: TextView
    private lateinit var categorySpinner: Spinner
    private lateinit var prioritySpinner: Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        val task = DataObject.getData(DataObject.currentData)

        updateTitle = view.findViewById(R.id.update_title)
        prioritySpinner = view.findViewById(R.id.update_priority)
        categorySpinner = view.findViewById(R.id.update_category)
        dateView = view.findViewById(R.id.date)
        pickDate = view.findViewById(R.id.update_date)

        delete = view.findViewById(R.id.delete_button)
        update = view.findViewById(R.id.update_button)

        val listOfCategory = getListOf("Category")
        val listOfPriority = getListOf("Priority")


        categorySpinner.adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listOfCategory) }
        categorySpinner.setSelection(1);


        prioritySpinner.adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listOfPriority) }



        Log.d("In Update", task.toString())




        updateTitle.setText(task.taskTitle)


//      Date Picker
        dateView.text = task.date
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
            DataObject.deleteData(DataObject.currentData)
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }

        update.setOnClickListener {
            if (updateTitle.text.toString().trim { it <= ' ' }.isNotEmpty()) {
                val title = updateTitle.text.toString()
                val priority = prioritySpinner.selectedItem.toString()
                val date = dateView.text.toString()
                val category = categorySpinner.selectedItem.toString()
                val newTask = Task(title,priority,date,category)
                DataObject.updateData(DataObject.currentData,newTask)
                findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
            }

        }
        return view
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