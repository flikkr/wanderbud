package com.kazymir.tripweaver.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.fragment.StatisticsFragment
import com.kazymir.tripweaver.fragment.TripFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerInit(savedInstanceState)
        dbInit()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.my_trips -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                TripFragment()
            ).commit()
            R.id.statistics -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                StatisticsFragment()
            ).commit()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun drawerInit(savedInstanceState: Bundle?) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                TripFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_view)
        }
    }

    private fun dbInit() {
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "tripweaver"
        ).build()
    }
}
