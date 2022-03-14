package com.example.canliskorapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View.MeasureSpec
import androidx.appcompat.widget.AppCompatImageView


class DynamicImageView(context: Context?, attrs: AttributeSet?) :
    AppCompatImageView(context!!, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val d = this.drawable
        if (d != null) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height =
                Math.ceil((width * d.intrinsicHeight.toFloat() / d.intrinsicWidth).toDouble())
                    .toInt()
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}