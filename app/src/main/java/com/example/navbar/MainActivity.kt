package com.example.navbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.lang.Exception
import com.example.navbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Declare the View Binding property
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var bottomNav: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize View Binding
        setContentView(binding.root)

        loadFragment(HomeFragment())

        // Find the DrawerLayout and NavigationView using View Binding
        drawerLayout = binding.myDrawerLayout
        bottomNav = binding.bottomNav



        // Set up the ActionBarDrawerToggle
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Inflate the custom header layout for the NavigationView
        val headerView = layoutInflater.inflate(R.layout.drawer_header, bottomNav, false)
        bottomNav.addHeaderView(headerView)

        // To make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Set up the NavigationView item selection listener
        bottomNav.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "You have selected home", Toast.LENGTH_SHORT).show()
                    loadFragment(HomeFragment())
                    selectMenuItem(menuItem)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.search -> {
                    Toast.makeText(this, "You have selected search", Toast.LENGTH_SHORT).show()
                    loadFragment(SearchFragment())
                    selectMenuItem(menuItem)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.favorite -> {
                    Toast.makeText(this, "You have selected favorite", Toast.LENGTH_SHORT).show()
                    loadFragment(FavoritesFragment())
                    selectMenuItem(menuItem)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.profile -> {
                    Toast.makeText(this, "You have selected profile", Toast.LENGTH_SHORT).show()
                    loadFragment(ProfileFragment())
                    selectMenuItem(menuItem)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
        binding.showDrawerButton.setOnClickListener {
            showNavigationDrawer()
        }
    }

    private fun selectMenuItem(menuItem: MenuItem) {
        bottomNav.menu.setGroupCheckable(0, true, true)
        menuItem.isChecked = true
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
    private fun showNavigationDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }
}