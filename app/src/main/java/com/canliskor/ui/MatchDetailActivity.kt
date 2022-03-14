package com.canliskor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.canliskor.model.match_detail.MatchDetail
import com.example.canliskorapp.R
import com.example.canliskorapp.util.CreateToken
import com.example.canliskorapp.util.Preferences.savePrefs
import com.example.canliskorapp.util.RetrofitOperations
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetailActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var viewPager : ViewPager
    lateinit var tabs : TabLayout
    lateinit var homeLogo : ImageView
    lateinit var awayLogo : ImageView
    lateinit var matchData : TextView
    lateinit var homeScore : TextView
    lateinit var awayScore : TextView
    lateinit var homeName : TextView
    lateinit var awayName : TextView
    lateinit var matchStatus : TextView
    private var matchId : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

       matchId = savePrefs().getInt("matchId",0)

        homeLogo = findViewById(R.id.matchHighlightsHomeLogo)
        awayLogo = findViewById(R.id.matchHighlightsAwayLogo)
        matchData = findViewById(R.id.matchHighlightsMatchDate)
        homeScore = findViewById(R.id.matchHighlightsHomeScore)
        awayScore = findViewById(R.id.matchHighlightsAwayScore)
        homeName = findViewById(R.id.matchHighlightsHomeName)
        awayName = findViewById(R.id.matchHighlightsAwayName)
        matchStatus = findViewById(R.id.matchHighlightsMatchStatus)

        viewPager = findViewById(R.id.viewPager)
        tabs = findViewById(R.id.tabs)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getApiResponse()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpTabs(responseTemp: MatchDetail){
        val bundle1 = Bundle()
        bundle1.putString("responseTemp", Gson().toJson(responseTemp))

        /*val adapter = ViewPagerAdapter()
        adapter.addFragment(MatchSummaryFragment(bundle1),"Özet")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.home_icon)*/

    }

    private fun getApiResponse() {
        var tokenString = CreateToken.GetAESToken()
        RetrofitOperations.instance.getMatchDetail(
            ContentType = "application/x-www-form-urlencoded", Token = tokenString.toString(),matchId
        ).enqueue(object : Callback<MatchDetail> {
            override fun onResponse(call: Call<MatchDetail>, response: Response<MatchDetail>) {
                when (response.code()) {
                    200 -> {
                        val responseTemp: MatchDetail = response.body()!!
                        Log.d("RESPONSE", "" + responseTemp)

                        matchData.text = responseTemp.match.scheduledDate
                        homeScore.text = responseTemp.match.homeScore.toString()
                        awayScore.text = responseTemp.match.awayScore.toString()
                        homeName.text = responseTemp.match.homeTeam.name
                        awayName.text = responseTemp.match.awayTeam.name
                        matchStatus.text = responseTemp.match.matchTime
                        Glide.with(applicationContext).load(responseTemp.match.homeTeam.logo.big).into(homeLogo)
                        Glide.with(applicationContext).load(responseTemp.match.awayTeam.logo.big).into(awayLogo)

                        setUpTabs(responseTemp)

                    }
                    else -> {
                        Toast.makeText(
                            applicationContext,
                            response.code().toString() + " " + response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<MatchDetail>, t: Throwable) {
                //Toast.makeText(binding.root.context,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}