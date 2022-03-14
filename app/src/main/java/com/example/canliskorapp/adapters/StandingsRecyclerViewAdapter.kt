package com.example.canliskorapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canliskorapp.R
import com.canliskor.model.standings.Standings
import com.canliskor.utils.Extensions
import com.canliskor.utils.Extensions.managerType
import kotlinx.android.synthetic.main.tournament_row.view.*

class StandingsRecyclerViewAdapter(val standingsItems : Standings)  :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val colors: Array<String> =
        arrayOf("#FFFFFFFF")

    inner class StandingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindStandings(standingsModels: Standings, colors: Array<String>, position: Int) {
            itemView.setOnClickListener {
                lst?.onItemClick(standingsModels)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 1]))
            val xModel = standingsModels.tournament
            val yModel = standingsModels.groups
            itemView.apply {
                tournament_name.text = xModel.seasonName
                matches_recyclerView.apply {
                    adapter = StandingsAdapter(yModel[position].standings)
                    layoutManager = managerType(1, 1)
                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StandingsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tournament_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StandingsRecyclerViewAdapter.StandingsViewHolder).bindStandings(standingsItems, colors, position)
    }

    override fun getItemCount(): Int = standingsItems.groups.size

    override fun getItemViewType(position: Int): Int = type!!



    interface Listener {
        fun onItemClick(standingsModels: Standings)

    }

    var lst: Listener? = null
    fun setListener(listener: Listener) {
        this.lst = listener
    }

    var type : Int? = 1
    fun setType(t : Int){
        this.type = t
    }
}
