package com.winteryy.nbcchallengekakaoapi.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.winteryy.nbcchallengekakaoapi.presentation.bookmark.BookmarkFragment
import com.winteryy.nbcchallengekakaoapi.presentation.search.SearchFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            1 -> BookmarkFragment()
            else -> SearchFragment()
        }
    }

}