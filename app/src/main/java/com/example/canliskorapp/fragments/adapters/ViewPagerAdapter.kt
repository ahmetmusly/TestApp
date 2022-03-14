package com.example.canliskorapp.fragments.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.canliskor.model.Matches
import com.canliskor.model.fixture.Fixtures

open class ViewPagerAdapter(fragment: Fragment, private var responseTemp : Fixtures) : FragmentStateAdapter(fragment) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    fun getCount(): Int {
        return mFragmentList.size
    }

    /*fun getItem(position: Int): Fragment {

    }*/

    fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    /*fun addFragment(fragment: Fragment, title : String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }*/


    fun weekCounter(): Int {
        return responseTemp!!.fixtures.size
    }

    fun getWeekNameList(): MutableList<String> {
        val weeksList : MutableList<String> = mutableListOf()
        for (item in responseTemp!!.fixtures){
            weeksList.add(item.week)
        }
        return weeksList

    }

    fun getWeeklyFixture(position: Int): List<Matches> {
        return responseTemp.fixtures[position].matches
    }

    override fun getItemCount(): Int {
        return weekCounter()
    }

    override fun createFragment(position: Int): Fragment {
       /* mFragmentList.add(fragment)
        mFragmentTitleList.add(title)*/
        return Fragment()
    }


}