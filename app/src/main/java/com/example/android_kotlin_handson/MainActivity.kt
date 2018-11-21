package com.example.android_kotlin_handson

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = { item ->
        when (item.getItemId()) {
            R.id.navigation_item1 -> {
                loadFragment(RepositoryListFragment.newInstance("Java"))
                return true
            }
            R.id.navigation_item2 -> {
                loadFragment(RepositoryListFragment.newInstance("Kotlin"))
                return true
            }
            R.id.navigation_item3 -> {
                loadFragment(RepositoryListFragment.newInstance("Scala"))
                return true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_item1
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            return true
        }
        return false
    }
}
