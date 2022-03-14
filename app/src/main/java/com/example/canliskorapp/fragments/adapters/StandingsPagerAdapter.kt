package com.example.canliskorapp.fragments.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.canliskor.model.standings.Standings

open class StandingsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var standings: Standings? = null

    override fun getCount(): Int = standings!!.groups.size ?: 0

    override fun getItem(position: Int): Fragment = StandingsChildFragment(standings!!)

    override fun getPageTitle(position: Int): CharSequence? = "P" + position//standingsModel!!.com.example.canliskorapp.models.FixturePackage.Tournament.Name


    fun updateData(standings: Standings) {
        this.standings = standings
    }
}
