package com.example.canliskorapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canliskor.model.Matches
import com.example.canliskorapp.R
import kotlinx.android.synthetic.main.row_home_child_item.view.*

class FixtureMatchesAdapter(val matches: List<Matches>)  :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val colors: Array<String> =
        arrayOf("#FFFFFF","#dddddd")

    inner class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindFixture(matches: Matches, colors: Array<String>, position: Int) {
            itemView.setOnClickListener {
                lst?.onItemClick(matches)
            }
            //itemView.setBackgroundColor(Color.parseColor(colors[position % 2]))
            itemView.apply {
                /*Glide.with(context).load(matches.com.example.canliskorapp.models.FixturePackage.HomeTeam.com.example.canliskorapp.models.FixturePackage.Logo.Big).into(scheduleHomeLogo)
                Glide.with(context).load(matches.com.example.canliskorapp.models.FixturePackage.AwayTeam.com.example.canliskorapp.models.FixturePackage.Logo.Big).into(scheduleAwayLogo)*/
                tv_home_name.text = matches.homeTeam.name
                tv_away_name.text = matches.awayTeam.name
                //scheduleHomeScore.text = matches.HomeScore.toString()
                //scheduleAwayScore.text = matches.AwayScore.toString()
                tv_match_time.text = matches.matchTime
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FixtureViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.fixture_row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FixtureMatchesAdapter.FixtureViewHolder).bindFixture(matches[position], colors, position)
    }

    override fun getItemCount(): Int = matches.size

    override fun getItemViewType(position: Int): Int = type!!



    interface Listener {
        fun onItemClick(matches: Matches)

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
