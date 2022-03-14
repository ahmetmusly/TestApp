package com.example.canliskorapp.fragments.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canliskorapp.R
import com.example.canliskorapp.adapters.StandingsRecyclerViewAdapter
import com.example.canliskorapp.fragments.SecondBaseFragment
import com.canliskor.model.standings.Standings
import com.canliskor.utils.Extensions
import com.canliskor.utils.Extensions.managerType

class StandingsChildFragment(val standings : Standings) : SecondBaseFragment (){

    lateinit var standingsRecyclerView: RecyclerView
    private var recyclerViewAdapter : StandingsRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.standings_view, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        standingsRecyclerView = view.findViewById(R.id.standingsRecyclerView)
        val layoutManager = managerType(1, 1)
        standingsRecyclerView.layoutManager = layoutManager

        setupData()
    }

    private fun setupData() {

        recyclerViewAdapter = StandingsRecyclerViewAdapter(standings)
        recyclerViewAdapter!!.setType(StandingsRecyclerViewAdapter.VIEW_TYPE_ONE)
        standingsRecyclerView.adapter = recyclerViewAdapter
    }

}