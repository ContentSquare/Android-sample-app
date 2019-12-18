package com.example.androidsampleapp.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: MainFragmentActivity) : FragmentStateAdapter(activity) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentListTitle = ArrayList<String>()

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun getPageTitle(position: Int): CharSequence {
        return mFragmentListTitle[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentListTitle.add(title)
    }

}
