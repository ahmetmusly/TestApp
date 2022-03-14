package com.canliskor.adapters


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */

/*
class SliderAdapter : BaseAdapter(R.layout.row_slider_item) {

    var extra: Extras? = null
    public override fun setExtras(extras: Extras) {
        this.extra = extras
    }

    public override val differ = AsyncListDiffer(this, diffCallBack)

    @SuppressLint("SetTextI18n")
    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.apply {
            val trendData = list as List<Data>
            tvTitle.text = "#${trendData[position].keyword}"
            recyclerview_slider.apply {
                adapter = TrendAdapter().also {
                    it.differ.submitList(trendData[position].items)
                    it.setExtras(extra!!)
                }
                adapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                    var lastX = 0
                    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                        when (e.action) {
                            MotionEvent.ACTION_DOWN -> lastX = e.x.toInt()
                            MotionEvent.ACTION_MOVE -> {
                                val isScrollingRight = e.x < lastX
                                if ((isScrollingRight && (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == adapter?.itemCount!! - 1) ||
                                    !isScrollingRight && (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0
                                ) {
                                    extra?.onTouch()?.userInputEnabled(true)
                                } else {
                                    extra?.onTouch()?.userInputEnabled(false)
                                }
                            }
                            MotionEvent.ACTION_UP -> {
                                lastX = 0
                                extra?.onTouch()?.userInputEnabled(true)
                            }
                        }
                        return false
                    }

                    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
                    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
                })
            }
        }
    }
}*/