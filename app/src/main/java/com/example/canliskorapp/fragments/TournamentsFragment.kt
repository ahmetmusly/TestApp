package com.example.canliskorapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.canliskorapp.util.CreateToken
import com.example.canliskorapp.util.RetrofitOperations
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.canliskorapp.R
import com.example.canliskorapp.fragments.adapters.FixturePagerAdapter
import com.canliskor.model.fixture.Fixture
import com.canliskor.model.tournaments.Tournaments


open class TournamentsFragment : SecondBaseFragment(), TabLayout.OnTabSelectedListener {

    lateinit var viewPager: ViewPager
    lateinit var tabs: TabLayout
    var tournamentId: Int? = 0
    var vPagerAdapter: FixturePagerAdapter? = null


    private var arrayAdapter: ArrayAdapter<String>? = null
    private var itemList: List<String>? = null
    private var fixtureList: List<Fixture>? = null
    private var seasonNameList: List<com.canliskor.model.tournaments.Tournament>? =
        null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_tournaments, container, false)
        val fixture_view: View = inflater.inflate(R.layout.header_view_second, container, false)

        SetViewHeader(R.layout.header_view_second)
        var fixture_view_layout = view.findViewById<RelativeLayout>(R.id.fixture_view)
        getViewHeader().layoutParams =
            RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 400)
        fixture_view_layout.addView(getViewHeader())

        /*fixtureRecyclerView = view.findViewById(R.id.fixtureRecyclerView)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fixtureRecyclerView.layoutManager = layoutManager*/

        tabs = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.viewPager)
        vPagerAdapter = FixturePagerAdapter(parentFragmentManager)
        tabs.setupWithViewPager(viewPager)


        getApiResponse()

        return view
    }

    var responseTempTournaments: Tournaments? = null
    private fun getApiResponse() {
        var tokenString = CreateToken.GetAESToken()
        RetrofitOperations.instance.getDataTournaments(
            ContentType = "application/x-www-form-urlencoded",
            Token = tokenString.toString()
        ).enqueue(object : Callback<Tournaments> {
            override fun onResponse(
                call: Call<Tournaments>,
                response: Response<Tournaments>
            ) {
                when (response.code()) {
                    200 -> {
                        responseTempTournaments = response.body()!!
                        //Log.d("TournamentResponse", "" + responseTemp)

                        //vPagerAdapter?.updateData(responseTemp!!)

                        viewPager.adapter = vPagerAdapter

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

            override fun onFailure(call: Call<Tournaments>, t: Throwable) {

            }
        })
    }


    override fun onTabSelected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
}

