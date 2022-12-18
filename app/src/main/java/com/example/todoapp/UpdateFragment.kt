package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment


class UpdateFragment : Fragment(){
    private lateinit var update: Button
    private lateinit var delete: Button
    private lateinit var title: EditText
    private lateinit var pirority: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // retrieve data from the bundle here, if it exists


    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // save data to the bundle here
        outState.putString("existing_task", "some data")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myData = savedInstanceState?.getString("existing_task")
        Log.d("In Update", "Am ajuns")
        if(myData!=null){
            Log.d("In Update", myData)
            title.setText(myData)
        }
    }
}