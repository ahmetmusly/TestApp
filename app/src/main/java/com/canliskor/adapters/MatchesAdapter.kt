package com.canliskor.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AsyncListDiffer
import com.canliskor.model.Matches
import com.canliskor.utils.Extensions.loadImage
import com.canliskor.utils.Extensions.myLog
import com.canliskor.utils.CSExtras
import com.canliskor.utils.CSPreferenceHelper.get
import com.canliskor.utils.CSPreferenceHelper.savePrefs
import com.canliskor.utils.CSPreferenceHelper.set
import com.canliskor.utils.Extensions.toast
import com.example.canliskorapp.R
import kotlinx.android.synthetic.main.row_home_child_item.view.*


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


@Suppress("UNCHECKED_CAST")
class MatchesAdapter : BaseAdapter(R.layout.row_home_child_item) {

    var extra: CSExtras? = null
    public override fun setExtras(CSExtras: CSExtras) {
        this.extra = CSExtras
    }

    public override val differ = AsyncListDiffer(this, diffCallBack)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.apply {
            val matches = list as List<Matches>

            val match = matches[position]

            tv_home_name.text = match.homeTeam.name
            tv_away_name.text = match.awayTeam.name
            tv_away_score.text = "${match.awayScore} : ${match.awayScore}"
            tv_match_time.text = match.matchTime
//if (match.Channel != null) match.Channel.logo.toString() else
            iv_match_channel.loadImage(match.homeTeam.logo.big)

            iv_star.apply {
                setOnClickListener {
                    val isChecked = context.savePrefs()["iv_star" + match.id, false]
                    context.savePrefs()["iv_star" + match.id] = !isChecked
                    notifyItemChanged(position)
                    context.toast(match.homeTeam.name)
                }

                val isChecked = context.savePrefs()["iv_star" + match.id, false]
                if (isChecked) setImageResource(R.drawable.ic_star_fill)
                else setImageResource(R.drawable.ic_star)
            }

            setOnClickListener {
                myLog(match.awayTeam.name)
                myLog("extra : $extra")
                extra?.onclick()?.it(match)
            }
        }
    }
}