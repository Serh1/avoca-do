package com.example.todoapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.adapter.TaskAdapter
import com.example.todoapp.data.DataObject


class MainFragment : Fragment() {
    private lateinit var adapter: TaskAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MainFragments", "We are Here")
        val view = inflater.inflate(R.layout.fragment_main_old, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler_main)
        recycler.setHasFixedSize(true)

        val addTaskButton = view.findViewById<Button>(R.id.add)
        addTaskButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createFragment)
        }

        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        recycler.adapter = TaskAdapter(DataObject.getAllData()) { dataObject ->
            findNavController().navigate(R.id.action_mainFragment_to_updateFragment)
        }
    }
}