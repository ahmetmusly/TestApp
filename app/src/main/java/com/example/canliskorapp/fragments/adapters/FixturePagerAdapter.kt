package com.example.canliskorapp.fragments.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.canliskor.model.fixture.Fixtures

open class FixturePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var fixtures: Fixtures? = null

    override fun getCount(): Int = fixtures?.fixtures?.size ?: 0

    override fun getItem(position: Int): Fragment = FixtureChildFragment(fixtures!!.fixtures[position])

    override fun getPageTitle(position: Int): CharSequence? = fixtures!!.fixtures[position].week

    fun updateData(fixtures: Fixtures) {
        this.fixtures = fixtures
    }
}
