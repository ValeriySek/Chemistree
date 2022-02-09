package com.selflearning.chemistree.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.selflearning.chemistree.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            hideBottomAppBar(destination, navView)
        }
    }

    private fun hideBottomAppBar(destination: NavDestination, navView: BottomNavigationView) {
        navView.isVisible = when (destination.id) {
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_games_pack,
            R.id.navigation_notifications,
            R.id.navigation_statistic -> true
            else -> false
        }
    }
}