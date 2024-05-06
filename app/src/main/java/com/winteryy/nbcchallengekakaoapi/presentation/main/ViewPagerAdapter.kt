package com.winteryy.nbcchallengekakaoapi.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.winteryy.nbcchallengekakaoapi.R
import com.winteryy.nbcchallengekakaoapi.presentation.bookmark.BookmarkFragment
import com.winteryy.nbcchallengekakaoapi.presentation.search.SearchFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        TabFragmentModel(SearchFragment.newInstance(), R.string.search_fragment_title),
        TabFragmentModel(BookmarkFragment.newInstance(), R.string.bookmark_fragment_title)
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    fun getTitle(position: Int): Int {
        return fragments[position].title
    }
}