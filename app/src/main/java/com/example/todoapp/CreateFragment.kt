package com.example.todoapp

import android.app.DatePickerDialog
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.todoapp.list.Category
import com.example.todoapp.data.DataObject
import com.example.todoapp.list.Priority
import com.google.android.gms.location.FusedLocationProviderClient
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreateFragment : Fragment() {

    private lateinit var save: Button
    private lateinit var newTitle: EditText
    private lateinit var pickDate: Button
    private lateinit var date: TextView
    private lateinit var categorySpinner: Spinner
    private lateinit var prioritySpinner: Spinner



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("CreateFragment", "Here")
        val view = inflater.inflate(R.layout.fragment_create, container, false)
        save = view.findViewById(R.id.save_button)
        newTitle = view.findViewById(R.id.create_title)

        val listOfCategory = getListOf("Category")
        val listOfPriority = getListOf("Priority")

        Log.d("Create", listOfCategory.toString())

        categorySpinner = view.findViewById(R.id.create_category)
        prioritySpinner = view.findViewById(R.id.create_priority)

        categorySpinner.adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listOfCategory) }
        prioritySpinner.adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listOfPriority) }



        var categoryValue = "none"
        categorySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categoryValue = parent!!.getItemAtPosition(position).toString()
            }
        }

        var priorityValue = "none"
        prioritySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                priorityValue = parent!!.getItemAtPosition(position).toString()
            }
        }

        pickDate = view.findViewById(R.id.pick_date)
        date = view.findViewById(R.id.date)

//      Date Picker
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
//        TODO(time): refactor code
        save.setOnClickListener {
            if (newTitle.text.toString().trim { it <= ' ' }.isNotEmpty()) {
                val title = newTitle.text.toString()
                val priority = priorityValue
                val date = date.text.toString()
                val category = categoryValue
                DataObject.setData(title, priority, date, category)
//                GlobalScope.launch {
//                    database.dao().insertTask(Entity(0, title, priority))
//
//                }
                findNavController().navigate(R.id.action_createFragment_to_mainFragment)
            }
        }
        return view
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
        date.text = sdf.format(myCalendar.time)
    }
}