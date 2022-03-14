package com.example.canliskorapp.fragments.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canliskorapp.R
import com.example.canliskorapp.adapters.FixtureMatchesAdapter
import com.example.canliskorapp.adapters.FixtureRecyclerViewAdapter
import com.example.canliskorapp.fragments.SecondBaseFragment
import com.canliskor.model.fixture.Fixture
import com.canliskor.utils.Extensions
import com.canliskor.utils.Extensions.managerType

class FixtureChildFragment(val fixture : Fixture) : SecondBaseFragment (){

    lateinit var fixtureRecyclerView: RecyclerView
    private var recyclerViewAdapter : FixtureMatchesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fixture_view, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fixtureRecyclerView = view.findViewById(R.id.fixtureRecyclerView)
        val layoutManager = managerType(1, 1)
        fixtureRecyclerView.layoutManager = layoutManager

        setupData()
    }

    private fun setupData() {
        recyclerViewAdapter = FixtureMatchesAdapter(fixture.matches)
        recyclerViewAdapter!!.setType(FixtureRecyclerViewAdapter.VIEW_TYPE_ONE)
        fixtureRecyclerView.adapter = recyclerViewAdapter
    }

}