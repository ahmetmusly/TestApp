package com.canliskor.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.canliskor.ui.standing.allmatches.AllMatchesFragment


/**
 * Created by Ahmet_MUŞLUOĞLU on 2.01.2022
 */


/*
* class StandingPagerAdapter(
    fragmentList: MutableList<Fragment>?,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {*/
class StandingPagerAdapter(
    val fragmentList: MutableList<AllMatchesFragment>?,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

//    private var fragmentList: MutableList<Fragment>? = null
//
//    init {
//        this.fragmentList = ff!!
//    }

    override fun getItemCount(): Int = fragmentList?.size ?: 0

    override fun createFragment(position: Int): Fragment = fragmentList!![position]

//    public fun updateFragmentList(fragmentList: MutableList<Fragment>) {
//        this.fragmentList = fragmentList
//        notifyDataSetChanged()
//    }
}