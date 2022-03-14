package com.example.canliskorapp.fragments

import com.canliskor.model.fixture.Fixture
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.canliskor.model.fixture.Fixtures
import com.example.canliskorapp.util.CreateToken
import com.example.canliskorapp.util.RetrofitOperations
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.canliskorapp.fragments.adapters.ViewPagerAdapter

import com.example.canliskorapp.R
import com.example.canliskorapp.adapters.FixtureMatchesAdapter
import com.example.canliskorapp.fragments.adapters.FixturePagerAdapter


open class FixtureFragment : SecondBaseFragment(), TabLayout.OnTabSelectedListener, AdapterView.OnItemSelectedListener {
/*
    lateinit var fixtureRecyclerView: RecyclerView
    private var recyclerViewAdapter : FixtureRecyclerViewAdapter? = null*/
    lateinit var viewPager : ViewPager
    lateinit var tabs : TabLayout
    var tournamentIdList : List<Int>? = null
    var tournamentId : Int = 52
    private var viewPagerAdapter : ViewPagerAdapter? = null
    var  vPagerAdapter :FixturePagerAdapter?=null

    private var arrayAdapter: ArrayAdapter<String>? = null
    private var itemList: List<String>? = null
    private var fixtureList: List<Fixture>? = null
    private var seasonNameList: List<com.canliskor.model.tournaments.Tournament>? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_fixture, container, false)
        val fixture_view: View = inflater.inflate(R.layout.header_view_second, container, false)

        SetViewHeader(R.layout.header_view_second)
        var fixture_view_layout = view.findViewById<RelativeLayout>(R.id.fixture_view)
        getViewHeader().layoutParams=RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 400)
        fixture_view_layout.addView(getViewHeader())

        /*fixtureRecyclerView = view.findViewById(R.id.fixtureRecyclerView)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fixtureRecyclerView.layoutManager = layoutManager*/


        //setUpTabs(bundle = bundle)

        tabs = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.viewPager)
        vPagerAdapter = FixturePagerAdapter(parentFragmentManager)
        tabs.setupWithViewPager(viewPager)

        getApiResponse(tournamentId)

        return view
    }



    var responseTemp : Fixtures?=null
    private fun getApiResponse(tournamentId : Int){
        val tokenString = CreateToken.GetAESToken()
        RetrofitOperations.instance.getDataFixture(ContentType = "application/x-www-form-urlencoded", Token = tokenString.toString(),
            tournamentId = tournamentId,null).enqueue(object: Callback<Fixtures> {
            override fun onResponse(call: Call<Fixtures>, response: Response<Fixtures>) {
                when(response.code()){
                    200 -> {
                        responseTemp = response.body()!!
                        Log.d("RESPONSE", "" + responseTemp)

                        vPagerAdapter?.updateData(responseTemp!!)
                        viewPager.adapter = vPagerAdapter

                        fixtureList = responseTemp!!.fixtures

                        seasonNameList = seasonNames?.tournaments
                        itemList = seasonNameList?.map { it.currentSeason.name }
//                        arrayAdapter = ArrayAdapter(
//                            context!!, android.R.layout.simple_spinner_dropdown_item,
//                            itemList!!
//                        )

                        choose_league_spinner.adapter = arrayAdapter
                        choose_league_spinner.onItemSelectedListener = this@FixtureFragment


                   /*     viewPager = view!!.findViewById(R.id.viewPager)
                        val vPagerAdapter = FixturePagerAdapter(parentFragmentManager)

                        viewPager.adapter =vPagerAdapter()*/

                       /* viewPagerAdapter = ViewPagerAdapter(this@FixtureFragment, responseTemp!!)
                        viewPager.adapter = viewPagerAdapter
                        viewPager.offscreenPageLimit = 3 // kaldırınca ilk açılışta son dakika ve trend den bilgi gelmiyor


                        val tabLayout: TabLayout = view!!.findViewById(R.id.tabs)
                        tabLayout.addOnTabSelectedListener(this@FixtureFragment)
                        TabLayoutMediator(
                            tabLayout, viewPager
                        ) { tab: TabLayout.Tab, position: Int ->
                            tab.text = (Objects.requireNonNull(
                                viewPager.adapter
                            ) as ViewPagerAdapter).getWeekNameList()[position]
                        }.attach()*/

                       /* recyclerViewAdapter = FixtureRecyclerViewAdapter(responseTemp!!)
                        recyclerViewAdapter!!.setType(FixtureRecyclerViewAdapter.VIEW_TYPE_ONE)
                        fixtureRecyclerView.adapter = recyclerViewAdapter*/

                    }else -> {
                    Toast.makeText(context,response.code().toString()+" "+ response.message(), Toast.LENGTH_LONG).show()
                }
                }
            }
            override fun onFailure(call: Call<Fixtures>, t: Throwable) {
                //Toast.makeText(binding.root.context,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onTabSelected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    var lst: FixtureMatchesAdapter.Listener? = null
    fun setListener(listener: FixtureMatchesAdapter.Listener) {
        this.lst = listener
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

        /*tournamentIdList = seasonNames?.Tournaments?.map { it.Id }
        tournamentId2 = tournamentIdList?.get(position)
        tournamentId2?.let { getApiResponse2(it) }*/



    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        vPagerAdapter?.updateData(responseTemp!!)
    }


}

