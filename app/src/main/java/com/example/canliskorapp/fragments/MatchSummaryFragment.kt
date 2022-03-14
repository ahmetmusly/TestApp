package com.example.canliskorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canliskorapp.R
import com.example.canliskorapp.adapters.MatchSummaryAdapter
import com.canliskor.model.match_detail.MatchDetail
import com.canliskor.utils.Extensions
import com.canliskor.utils.Extensions.managerType
import com.google.gson.Gson


class MatchSummaryFragment(bundle: Bundle) : Fragment() {

    var bundle : Bundle? = null

    init {
        this.bundle = bundle
    }

    lateinit var match_summary_rec: RecyclerView
    private var matchSummaryAdapter: MatchSummaryAdapter? = null
    //lateinit var matchDetailItem : MatchDetailModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_match_summary, container, false)

        val responseBodyTemp = bundle?.getString("responseTemp")
        val matchDetailData : MatchDetail = Gson().fromJson(responseBodyTemp,MatchDetail::class.java)

        //Log.e("BERKAY",responseBody.toString())


        match_summary_rec = view.findViewById(R.id.match_summary_rec)
        val layoutManager = managerType(1,1)
        match_summary_rec.layoutManager = layoutManager


        matchSummaryAdapter = MatchSummaryAdapter(matchDetailData)
        matchSummaryAdapter!!.setType(MatchSummaryAdapter.VIEW_TYPE_ONE)
        //matchSummaryAdapter!!.setListener(MatchSummaryFragment)
        match_summary_rec.adapter = matchSummaryAdapter

        return view
    }

}