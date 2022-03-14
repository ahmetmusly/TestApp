package com.example.canliskorapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.canliskorapp.R
import com.canliskor.model.match_detail.MatchDetail
import kotlinx.android.synthetic.main.match_summary_row.view.*

class MatchSummaryAdapter (val matchDetailItems: MatchDetail) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            const val VIEW_TYPE_ONE = 1
            const val VIEW_TYPE_TWO = 2
        }

        private val colors: Array<String> =
            arrayOf("#FFFFFFFF")

        inner class TodaysMatchesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            fun bind(matchDetailItems: MatchDetail, colors: Array<String>, position: Int) {
                itemView.setBackgroundColor(Color.parseColor(colors[position % 1]))
                itemView.apply {
                    matchSummaryRowMinute.text = matchDetailItems.timeline[position].matchTime.toString()

                }

            }
        }

        /*inner class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(fixtureModels: com.example.canliskorapp.models.FixturePackage.FixtureModel, colors: Array<String>, position: Int) {
                itemView.setOnClickListener {
                    // lst?.onItemClick()
                }
                itemView.setBackgroundColor(Color.parseColor(colors[position % 1]))
                val yModel = fixtureModels.com.example.canliskorapp.models.FixturePackage.Fixture[position].Matches[position]
                itemView.apply {
                    // scheduleHomeName.text = yModel.com.example.canliskorapp.models.FixturePackage.HomeTeam.Name
                }
            }
        }*/

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return TodaysMatchesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_summary_row, parent, false))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if(type == VIEW_TYPE_ONE){
                (holder as TodaysMatchesViewHolder).bind(matchDetailItems, colors, position)
            }

        }

        override fun getItemCount(): Int = matchDetailItems.timeline.size

        override fun getItemViewType(position: Int): Int = type!!


        interface Listener {
            fun onItemClick(matchSummary: MatchDetail)

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

