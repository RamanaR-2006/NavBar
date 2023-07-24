package com.example.navbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
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


class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var bottomNav : NavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment())
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        bottomNav = findViewById<NavigationView>(R.id.bottomNav)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        val headerView = layoutInflater.inflate(R.layout.drawer_header, bottomNav, false)
        bottomNav.addHeaderView(headerView)

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val showDrawerButton: Button = findViewById(R.id.showDrawerButton)
        showDrawerButton.setOnClickListener {
            showNavigationDrawer()
        }

        bottomNav.setNavigationItemSelectedListener  {
            when (it.itemId) {
                R.id.home -> {
                    Toast.makeText(this,"You have selected home",Toast.LENGTH_SHORT).show()
                    loadFragment(HomeFragment())
                    selectMenuItem(it)
                    true
                }
                R.id.search -> {
                    Toast.makeText(this,"You have selected search",Toast.LENGTH_SHORT).show()
                    loadFragment(SearchFragment())
                    selectMenuItem(it)
                    true
                }
                R.id.favorite -> {
                    Toast.makeText(this,"You have selected favorite",Toast.LENGTH_SHORT).show()
                    loadFragment(FavoritesFragment())
                    selectMenuItem(it)
                    true
                }
                R.id.profile -> {
                    Toast.makeText(this,"You have selected profile",Toast.LENGTH_SHORT).show()
                    loadFragment(ProfileFragment())
                    selectMenuItem(it)
                    true
                }
                else -> {false}
            }
        }
    }
    private fun selectMenuItem(menuItem: MenuItem) {
        bottomNav.menu.setGroupCheckable(0, true, true)
        menuItem.isChecked = true}

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
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