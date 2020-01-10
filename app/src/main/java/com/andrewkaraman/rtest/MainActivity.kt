package com.andrewkaraman.rtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.andrewkaraman.rtest.net.loadLots
import com.andrewkaraman.rtest.ui.CSV.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    //TODO Add tests
    private var job: Deferred<Unit>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        job = GlobalScope.async { loadLots() }
    }

    override fun onResume() {
        super.onResume()
        job = GlobalScope.async { loadLots() }
    }

    override fun onPause() {
        super.onPause()
        job!!.cancel()
    }
}