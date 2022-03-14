package com.canliskor.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.canliskor.model.tournaments.Tournament
import com.canliskor.utils.Extensions.loadCircleImage
import com.canliskor.utils.Extensions.managerType
import com.canliskor.utils.CSExtras
import com.canliskor.utils.CSListType.HOME
import com.example.canliskorapp.R
import kotlinx.android.synthetic.main.home_tournament_row.view.*


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */

@Suppress("UNCHECKED_CAST")
class HomeAdapter : BaseAdapter(R.layout.home_tournament_row, HOME) {

    var extra: CSExtras? = null

    public override fun setExtras(CSExtras: CSExtras) {
        this.extra = CSExtras
    }

    public override val differ = AsyncListDiffer(this, diffCallBack)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.itemView.apply {

            val todayMatches = list as List<Tournament>

            val tournament = todayMatches[position]

            tournament.let {

                tournament_name.text = tournament.tournamentName

                country_logo.loadCircleImage(it.countryLogos.big)

                matches_recyclerView.apply {

                    val matchesAdapter = MatchesAdapter()

                    matchesAdapter.setExtras(extra!!)

                    matchesAdapter.differ.submitList(tournament.matches)

                    adapter = matchesAdapter

                    layoutManager = managerType(1, 1)

                    setHasFixedSize(true)
                }
            }
        }
    }
}