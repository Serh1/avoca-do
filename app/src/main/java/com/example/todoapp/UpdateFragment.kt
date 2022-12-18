package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.todoapp.model.Task

class UpdateFragment : Fragment() {
    private lateinit var update: Button
    private lateinit var delete: Button
    private lateinit var taskTitle: EditText
    private lateinit var pirority: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        val task: Task? = arguments?.getParcelable("objectKey")
        Log.d("In Update", task.toString())
//        TODO: Fix this
        taskTitle = view.findViewById(R.id.update_title)
        taskTitle.setText(task.toString())

//        delete.setOnClickListener {
//            if (newTitle.text.toString().trim { it <= ' ' }.isNotEmpty()
//                && newPriority.text.toString().trim { it <= ' ' }.isNotEmpty()
//            ) {
//                var title = newTitle.getText().toString()
//                var priority = newPriority.getText().toString()
//                DataObject.setData(title, priority)
////                GlobalScope.launch {
////                    database.dao().insertTask(Entity(0, title, priority))
////
////                }
//                findNavController().navigate(R.id.action_createFragment_to_mainFragment)
//            }
//        }
//        TODO(): Update the date
        return view
    }


}