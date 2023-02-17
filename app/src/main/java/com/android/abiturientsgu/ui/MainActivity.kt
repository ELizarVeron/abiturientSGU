package com.android.abiturientsgu.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.android.abiturientsgu.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var login: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d("OLOLO", "MainActivity onCreate")

        login = intent.getStringExtra("login")!!

        runApp()
        /* viewModel.getProfile()
         viewModel.liveData.observe(this){

             runApp()
         }*/

    }

    private fun runApp() {

        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment)
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupWithNavController(bottomNavigationView, navController)

        /*val bottomNavigationView =
            findViewById<View>(R.id.bottom_navigation) as BottomNavigationView

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_favorites -> {}
                R.id.action_schedules -> {}
                R.id.action_music -> {}
            }
            false
        }*/

        /*  val appBarConfiguration = AppBarConfiguration(
              setOf(
                  R.id.petFragment, R.id.tasksFragment, R.id.settingsFragment
              )
          )*/
        // setupWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}