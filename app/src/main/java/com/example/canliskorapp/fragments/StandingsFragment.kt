package com.example.canliskorapp.fragments

import com.canliskor.model.fixture.Fixture
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.canliskorapp.R
import com.example.canliskorapp.fragments.adapters.StandingsPagerAdapter
import com.canliskor.model.standings.Standings
import com.example.canliskorapp.util.CreateToken
import com.example.canliskorapp.util.RetrofitOperations
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingsFragment : SecondBaseFragment(), AdapterView.OnItemSelectedListener {

    /*lateinit var standingsRecyclerView: RecyclerView
    private var recyclerViewAdapter : StandingsRecyclerViewAdapter? = null*/
    var vPagerAdapter: StandingsPagerAdapter? = null
    lateinit var tabs: TabLayout
    lateinit var viewPager: ViewPager

    private var arrayAdapter: ArrayAdapter<String>? = null
    private var itemList: List<String>? = null
    private var fixtureList: List<Fixture>? = null
    private var seasonNameList: List<com.canliskor.model.tournaments.Tournament>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_standings, container, false)

        SetViewHeader(R.layout.header_view_second)


        /*standingsRecyclerView = view.findViewById(R.id.standingsRecyclerView)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        standingsRecyclerView.layoutManager = layoutManager*/

        viewPager = view.findViewById(R.id.viewPager)
        tabs = view.findViewById(R.id.tabs)
        vPagerAdapter = StandingsPagerAdapter(parentFragmentManager)
        tabs.setupWithViewPager(viewPager)

        getApiResponse()

        return view
    }

    private fun getApiResponse() {
        var tokenString = CreateToken.GetAESToken()
        RetrofitOperations.instance.getDataStandings(
            ContentType = "application/x-www-form-urlencoded", Token = tokenString.toString(),
            52
        ).enqueue(object : Callback<Standings> {
            override fun onResponse(call: Call<Standings>, response: Response<Standings>) {
                when (response.code()) {
                    200 -> {
                        val responseTemp: Standings = response.body()!!
                        Log.d("RESPONSE", "" + responseTemp)

                        vPagerAdapter?.updateData(responseTemp!!)

                        viewPager.adapter = vPagerAdapter


                        seasonNameList = seasonNames?.tournaments
                        itemList = seasonNameList?.map { it.currentSeason.name }


                        //açılacak burası tekrar

//                        itemList.let {
//                            arrayAdapter = ArrayAdapter(
//                                context!!, android.R.layout.simple_spinner_dropdown_item,
//                                it!!
//                            )
//                        }
                        choose_league_spinner.adapter = arrayAdapter
                        choose_league_spinner.onItemSelectedListener = this@StandingsFragment
/*
                        recyclerViewAdapter = StandingsRecyclerViewAdapter(responseTemp)
                        recyclerViewAdapter!!.setType(StandingsRecyclerViewAdapter.VIEW_TYPE_ONE)
                        standingsRecyclerView!!.adapter = recyclerViewAdapter*/

                    }
                    else -> {
                        Toast.makeText(
                            context,
                            response.code().toString() + " " + response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<Standings>, t: Throwable) {
                //Toast.makeText(binding.root.context,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //("Not yet implemented")
    }

}
