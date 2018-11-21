package com.example.android_kotlin_handson

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = { item: MenuItem  ->
        when (item.itemId) {
            R.id.navigation_item1 -> {
                loadFragment(RepositoryListFragment.newInstance("Java"))
                true
            }
            R.id.navigation_item2 -> {
                loadFragment(RepositoryListFragment.newInstance("Kotlin"))
                true
            }
            R.id.navigation_item3 -> {
                loadFragment(RepositoryListFragment.newInstance("Scala"))
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.navigation).run {
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            selectedItemId = R.id.navigation_item1
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        return fragment?.let {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
            true
        } ?: false
    }
}
