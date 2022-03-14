package com.canliskor.adapters


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021.
 */

/*
class PagerAdapter(extras: Extras?) : RecyclerView.Adapter<PagerAdapter.PageHolder>() {

    private val newsData = mutableListOf<Data>()

    private var extras: Extras? = null

    init {
        this.extras = extras
    }

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerViewToday: RecyclerView = view.recyclerview_today
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.apply {
            recyclerViewToday.apply {
                var firstIndex = position * PER_PAGE
                var lastIndex = (position + 1) * PER_PAGE
                if (newsData.size < lastIndex)
                    lastIndex = newsData.size - 1
                if (newsData.size <= firstIndex)
                    firstIndex = newsData.size - 1
//                val sliceData = newsData.slice(firstIndex..lastIndex)
                val sliceData = newsData.subList(firstIndex, lastIndex)
                adapter = TodayItemAdapter(sliceData,true).apply { setExtras(extras) }
                setHasFixedSize(true)
                layoutManager = managerType(1, 1)
            }
        }
    }

    override fun getItemCount(): Int = if (newsData.isNullOrEmpty()) 0 else newsData.size / PER_PAGE

    fun update(data: MutableList<Data>) {
        newsData.clear()
        newsData.addAll(data)
        notifyDataSetChanged()
    }
}*/