package com.shuttleclone.userapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ui.fragment.bookingFrgs.CancelledBookingFragment
import com.shuttleclone.userapp.ui.fragment.bookingFrgs.ScheduledBookingFragment


class BookingFragment :  Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)

        val adapter = TabPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }

    private inner class TabPagerAdapter(fm: androidx.fragment.app.FragmentManager) : FragmentPagerAdapter(fm ,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ScheduledBookingFragment()
                1 -> CancelledBookingFragment()
                else -> throw IllegalArgumentException("Invalid tab position")
            }
        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Scheduled"
                1 -> "Cancelled"
                else -> null
            }
        }
    }
}