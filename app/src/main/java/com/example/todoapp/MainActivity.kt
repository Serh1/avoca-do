package com.example.todoapp

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import com.example.todoapp.data.DataObject
import com.example.todoapp.data.TaskDatabase
import com.example.todoapp.model.Task
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.NavHostFragment)

        Timber.plant(Timber.DebugTree())

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navigationView)

        NavigationUI.setupActionBarWithNavController(this,navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        DataObject.setData("Wash Clotes","LOW","17-10-2022","HOME")
        DataObject.setData("Implement DAO","MEDIUM","12-10-2022","HOME")
        DataObject.setData("long messages that repeat itself long messages that repeat itself","HIGH","12-10-2022","HOME")
        DataObject.setData("long messages that repeat itself long messages that repeat itself","HIGH","12-10-2022","HOME")
        DataObject.setData("long messages that repeat itself long messages that repeat itself","HIGH","12-10-2022","HOME")
        DataObject.setData("long messages that repeat itself long messages that repeat itself","HIGH","12-10-2022","HOME")
        DataObject.setData("long messages that repeat itself long messages that repeat itself","HIGH","12-10-2022","HOME")

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.NavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
        }
    }
    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart Called")
    }
//    fun replaceFragment(homeFragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment,homeFragment)
//        fragmentTransaction.commit()
//    }

}