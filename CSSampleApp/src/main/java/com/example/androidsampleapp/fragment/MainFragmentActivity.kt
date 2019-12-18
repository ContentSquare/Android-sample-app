package com.example.androidsampleapp.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.androidsampleapp.R
import com.example.androidsampleapp.analytics.Analytics
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragmentActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager2Callback: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment_activity)
        val tabs: TabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.viewPager2)

        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter.addFragment(FragmentTab(getString(R.string.first_fragment)), "First Tab")
        pagerAdapter.addFragment(FragmentTab(getString(R.string.second_fragment)), "Second Tab")
        pagerAdapter.addFragment(FragmentTab(getString(R.string.third_fragment)), "Third Tab")

        viewPager.apply {
            adapter = pagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        viewPager2Callback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Analytics.tagScreen(pagerAdapter.getPageTitle(position).toString())
            }
        }
        viewPager.registerOnPageChangeCallback(viewPager2Callback)
        Analytics.tagScreen("Main-Fragment-Activity")
    }

    override fun onPause() {
        super.onPause()
        viewPager.unregisterOnPageChangeCallback(viewPager2Callback)
    }
}
