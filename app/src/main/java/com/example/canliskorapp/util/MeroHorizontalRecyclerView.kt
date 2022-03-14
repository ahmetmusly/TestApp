package com.example.canliskorapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MeroHorizontalRecyclerView : RecyclerView {
    private var mGestureDetector: GestureDetector? = null

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        setLayoutManager(layoutManager)
        mGestureDetector = GestureDetector(context, VerticalScrollDetector())
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        return if (mGestureDetector!!.onTouchEvent(e)) {
            false
        } else super.onInterceptTouchEvent(e)
    }

    inner class VerticalScrollDetector : SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return Math.abs(distanceY) > Math.abs(distanceX)
        }
    }
}
