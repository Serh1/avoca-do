package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.adapter.TaskAdapter
import com.example.todoapp.data.DataObject


class MainFragment : Fragment() {
    private lateinit var adapter: TaskAdapter
    private lateinit var recycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("MainFragments", "We are Here")
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)

        val addTask = view.findViewById<Button>(R.id.add)
        addTask.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler_main)
        recycler.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        recycler.adapter = TaskAdapter(DataObject.getAllData()){ dataObject ->
            val fragment = UpdateFragment()
            val bundle = Bundle()
            bundle.putString("existing_task", dataObject.toString())
            fragment.arguments = bundle
            findNavController().navigate(R.id.action_mainFragment_to_updateFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}