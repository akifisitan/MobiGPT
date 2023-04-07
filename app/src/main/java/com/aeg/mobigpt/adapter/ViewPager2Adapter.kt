package com.aeg.mobigpt.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aeg.mobigpt.fragment.ChatListFragment
import com.aeg.mobigpt.fragment.SettingsFragment

// Adapter for ViewPager
class ViewPager2Adapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    private val tabCount: Int
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ChatListFragment()
            else -> SettingsFragment()
        }
    }

    override fun getItemCount(): Int {
        return tabCount
    }
}