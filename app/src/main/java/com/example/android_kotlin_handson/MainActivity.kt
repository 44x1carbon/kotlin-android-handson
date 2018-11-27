package com.example.android_kotlin_handson

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = { item: MenuItem ->
        when (item.itemId) {
            R.id.navigation_item1 -> {
                loadFragment(RepositoryListFragment.newInstance("Java"))
            }
            R.id.navigation_item2 -> {
                loadFragment(RepositoryListFragment.newInstance("Kotlin"))
            }
            R.id.navigation_item3 -> {
                loadFragment(RepositoryListFragment.newInstance("Scala"))
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.run {
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            selectedItemId = R.id.navigation_item1
        }
    }

    private fun loadFragment(fragment: Fragment) =
            fragment.let {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, it)
                        .commit()
                true
            }
}
