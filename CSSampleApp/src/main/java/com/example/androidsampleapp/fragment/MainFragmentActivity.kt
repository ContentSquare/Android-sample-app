package com.example.androidsampleapp.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.androidsampleapp.R
import com.example.androidsampleapp.analytics.Analytics
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragmentActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment_activity)
        val tabs: TabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.viewPager2)

        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter.addFragment(FirstFragment(), "First Tab")
        pagerAdapter.addFragment(SecondFragment(), "Second Tab")
        pagerAdapter.addFragment(ThirdFragment(), "Third Tab")

        viewPager.apply {
            adapter = pagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        TabLayoutMediator(tabs, viewPager, true) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        viewPager.registerOnPageChangeCallback(onPageChangeListener)
        Analytics.tagScreen("Main-Fragment-Activity")
    }

    private val onPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            //NO-OP
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            //NO-OP
        }

        override fun onPageSelected(position: Int) {
            viewPager.adapter?.run {
                Analytics.tagScreen(pagerAdapter.getPageTitle(position).toString())
            }
        }
    }
}
