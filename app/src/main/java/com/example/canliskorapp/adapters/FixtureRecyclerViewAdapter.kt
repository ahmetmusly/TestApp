package com.example.canliskorapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canliskorapp.R
import com.canliskor.model.fixture.Fixture
import com.canliskor.model.fixture.Fixtures
import com.canliskor.utils.Extensions
import com.canliskor.utils.Extensions.managerType
import kotlinx.android.synthetic.main.tournament_row.view.*

class FixtureRecyclerViewAdapter(val fixtureItems : Fixture)  :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val colors: Array<String> =
        arrayOf("#FFFFFFFF")

    inner class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindFixture(fixtureModels: Fixtures, colors: Array<String>, position: Int) {
            itemView.setOnClickListener {
                lst?.onItemClick(fixtureModels)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 1]))
            val xModel = fixtureModels.fixtures[position]
            itemView.apply {
                tournament_name.text = xModel.week
                matches_recyclerView.apply {
                    adapter = FixtureMatchesAdapter(xModel.matches)
                    layoutManager = managerType(1, 1)
                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FixtureViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tournament_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //(holder as FixtureRecyclerViewAdapter.FixtureViewHolder).bindFixture(fixtureItems, colors, position)
    }

    override fun getItemCount(): Int = fixtureItems.matches.size

    override fun getItemViewType(position: Int): Int = type!!



    interface Listener {
        fun onItemClick(fixtureModels: Fixtures)

    }

    var lst: Listener? = null
    fun setListener(listener: Listener) {
        this.lst = listener
    }

    var type : Int? = 0
    fun setType(t : Int){
        this.type = t
    }
}
