package com.example.canliskorapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.canliskorapp.R
import com.canliskor.model.standings.Standing
import kotlinx.android.synthetic.main.standings_row_layout.view.*

class StandingsAdapter(val standings : List<Standing>)  :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val colors: Array<String> =
        arrayOf("#FFFFFFFF")

    inner class StandingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindStandings(standings: Standing, colors: Array<String>, position: Int) {
            itemView.setOnClickListener {
                lst?.onItemClick(standings)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 1]))
            val xModel = standings
            itemView.apply {
                standingsItemTeamTitle.text = xModel.team.name
                standingsItemPlayedTitle.text = xModel.played.toString()
                standingsItemWinsTitle.text = xModel.win.toString()
                standingsItemDrawsTitle.text = xModel.draw.toString()
                standingsItemLossesTitle.text = xModel.lost.toString()
                standingsItemScoredGoalTitle.text = xModel.goalsScored.toString()
                standingsItemAllowedGoalTitle.text = xModel.goalsConceded.toString()
                standingsItemPointTitle.text = xModel.points.toString()
                standingsItemTeamStanding.text = (position+1).toString()

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StandingsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.standings_row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StandingsAdapter.StandingsViewHolder).bindStandings(standings[position], colors, position)
    }

    override fun getItemCount(): Int = standings.size

    override fun getItemViewType(position: Int): Int = type!!



    interface Listener {
        fun onItemClick(standings: Standing)

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
