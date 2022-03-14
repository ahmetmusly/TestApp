package com.example.canliskorapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.canliskorapp.R
import com.canliskor.model.tournaments.Tournaments
import com.example.canliskorapp.util.CreateToken
import com.example.canliskorapp.util.RetrofitOperations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class SecondBaseFragment : Fragment() {

    lateinit var subTitle: TextView
    lateinit var secondHeaderView: View
    var container: ViewGroup? = null
    lateinit var choose_league_spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            getApiResponseTournaments()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_second_base, container, false)
        return view
    }

    var seasonNames: Tournaments? = null
    private fun getApiResponseTournaments() {
        var tokenString = CreateToken.GetAESToken()
        RetrofitOperations.instance.getDataTournaments(
            ContentType = "application/x-www-form-urlencoded",
            Token = tokenString.toString()
        ).enqueue(object :
            Callback<Tournaments> {
            override fun onResponse(call: Call<Tournaments>, response: Response<Tournaments>) {
                when (response.code()) {
                    200 -> {
                        seasonNames = response.body()!!

                        Log.d("TournamentResponseBase", "" + seasonNames)


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
                //Toast.makeText(binding.root.context,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    protected fun SetViewHeader(layout_id: Int) {
//        secondHeaderView = layoutInflater.inflate(layout_id, container, false)
//        choose_league_spinner = secondHeaderView.findViewById(R.id.choose_league_spinner)
//        subTitle = secondHeaderView.findViewById(R.id.subtitle)

        getApiResponseTournaments()
    }

    protected fun getViewHeader(): View {
        return secondHeaderView
    }

}