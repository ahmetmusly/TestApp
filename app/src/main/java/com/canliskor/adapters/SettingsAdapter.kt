package com.canliskor.adapters


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */

/*
class SettingsAdapter(
    private var list: List<Any>? = null,
    private var type: Int? = TYPE_1
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = list!![position]
        when (holder) {
            is ViewHolder1 -> holder.bind(item as Category)
            is ViewHolder2 -> holder.bind(item as Source)
            else -> throw IllegalArgumentException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_1 -> ViewHolder1(parent.inflate(R.layout.row_settings_category))
            TYPE_2 -> ViewHolder2(parent.inflate(R.layout.row_settings_category))
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun getItemViewType(position: Int): Int = if (type == TYPE_1) TYPE_1 else TYPE_2

    inner class ViewHolder1(itemView: View) : BaseViewHolder<Category>(itemView) {
        @SuppressLint("SetTextI18n")
        override fun bind(category: Category) {
            itemView.apply {
                tvTitleCategory.text = category.name
                tvTitleCategory.setOnClickListener { extras?.onclick()?.it(category) }
            }
        }
    }

    inner class ViewHolder2(itemView: View) : BaseViewHolder<Source>(itemView) {
        @SuppressLint("SetTextI18n")
        override fun bind(source: Source) {
            itemView.apply {
                tvTitleCategory.text = source.name
//                if (adapterPosition == itemCount - 1) viewLine1.gone() else viewLine1.visible()

                tvTitleCategory.setOnClickListener { extras?.onclick()?.it(source) }
            }
        }
    }

    fun update(listAny: List<Any>? = null) {
        this.list = listAny
        notifyDataSetChanged()
    }

    private var extras: Extras? = null
    fun setExtras(extras: Extras?) {
        this.extras = extras
    }
}*/