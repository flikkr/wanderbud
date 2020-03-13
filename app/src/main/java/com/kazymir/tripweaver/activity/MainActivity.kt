package com.kazymir.tripweaver.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /*
         * We could use `AppBarConfiguration(nav_view.menu, drawer_layout)` instead, but since the
         * Share and Send items are nested, they won't be treated as top-level destinations.
         */
        val topLevelDestinations = setOf(
            R.id.nav_all_trips,
            R.id.nav_statistics,
            R.id.nav_about,
            R.id.nav_achievements,
            R.id.nav_settings
        )
        appBarConfig = AppBarConfiguration(topLevelDestinations, drawer_layout)

        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfig)
        nav_view.setupWithNavController(navController)
    }
    
    /** Ask the NavController to handle "navigate up" events. */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    /** Close the drawer when hardware back is pressed. */
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
