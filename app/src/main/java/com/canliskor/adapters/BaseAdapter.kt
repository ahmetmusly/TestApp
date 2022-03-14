package com.canliskor.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.canliskor.model.Matches
import com.canliskor.utils.Extensions.inflate
import com.canliskor.utils.CSExtras
import com.canliskor.utils.CSListType
import com.canliskor.utils.CSListType.*
import com.canliskor.model.today_matches.TodayMatches


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


abstract class BaseAdapter(private val layoutId: Int, type: CSListType? = DEFAULT) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    protected abstract fun setExtras(CSExtras: CSExtras)

    protected abstract val differ: AsyncListDiffer<Any>

    protected val diffCallBack = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when (type) {
                HOME -> (oldItem as TodayMatches).tournaments == (newItem as TodayMatches).tournaments
                MATCHES -> (oldItem as Matches).id == (newItem as Matches).id
//                LAST_MINUTE -> (oldItem as com.canliskor.models.news.Data).id == (newItem as com.canliskor.models.news.Data).id
                else -> true
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when (type) {
                HOME -> (oldItem as TodayMatches).hashCode() == (newItem as TodayMatches).hashCode()
                MATCHES -> (oldItem as Matches).hashCode() == (newItem as Matches).hashCode()
//                LAST_MINUTE -> (oldItem as com.canliskor.models.news.Data).hashCode() == (newItem as com.canliskor.models.news.Data).hashCode()
                else -> true
            }
        }
    }

    var list: List<Any>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(parent.inflate(layoutId))

    override fun getItemCount(): Int = list.size

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }
}