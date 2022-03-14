package com.canliskor.adapters

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import com.canliskor.model.standings.Standing
import com.canliskor.utils.CSExtras
import com.canliskor.utils.CSListType.HOME
import com.example.canliskorapp.R
import kotlinx.android.synthetic.main.row_standing_item.view.*


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */

@Suppress("UNCHECKED_CAST")
class AllMatchesAdapter : BaseAdapter(R.layout.row_standing_item, HOME) {

    var extra: CSExtras? = null

    public override fun setExtras(CSExtras: CSExtras) {
        this.extra = CSExtras
    }

    public override val differ = AsyncListDiffer(this, diffCallBack)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.apply {
            val standings = list as List<Standing>

            val standing = standings[position]

            standing.let {

                val circleColorID = when (position) {
                    in 0..1 -> R.color.circle1
                    in 2..3 -> R.color.circle2
                    in 15..itemCount -> R.color.circle3
                    else -> R.color.white
                }

                val textColor = when (position) {
                    in 0..3 -> R.color.white
                    in 15..itemCount -> R.color.white
                    else -> R.color.black
                }

                val fontName = if (position > 3) R.string.barlow_medium else R.string.barlow_bold
                val color = ContextCompat.getColor(context, circleColorID)

                tv_count.text = position.plus(1).toString()
                tv_count.backgroundTintList = ColorStateList.valueOf(color)
                tv_count.setBackgroundResource(R.drawable.ic_circle)
                tv_count.setTextColor(ContextCompat.getColor(context, textColor))

                tv_team_name.setCustomFont(context, context.getString(fontName))
                tv_team_name.text = standing.team.name

                tv_O.text = standing.played.toString()
                tv_G.text = standing.win.toString()
                tv_B.text = standing.draw.toString()
                tv_M.text = standing.lost.toString()
                tv_A.text = standing.goalsScored.toString()
                tv_Y.text = standing.goalsConceded.toString()
                tv_P.text = standing.points.toString()

                if (position % 2 == 0)
                    iv_status.setImageResource(R.drawable.ic_up)
                else
                    iv_status.setImageResource(R.drawable.ic_down)
            }
        }
    }
}