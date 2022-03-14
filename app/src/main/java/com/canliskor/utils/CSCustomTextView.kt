package com.canliskor.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.content.res.TypedArray
import com.example.canliskorapp.R
import android.graphics.Typeface
import android.util.Log
import com.canliskor.utils.CSCustomTextView
import com.canliskor.utils.Extensions.myLog
import java.lang.Exception

class CSCustomTextView : AppCompatTextView {

    constructor(context: Context?) : super(context!!)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        setCustomFont(context, attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun setCustomFont(ctx: Context, attrs: AttributeSet?) {

        val a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView)

        val customFont = a.getString(R.styleable.CustomTextView_fontFamily)

        setCustomFont(ctx, customFont)

        a.recycle()
    }

    fun setCustomFont(ctx: Context, fontName: String?): Boolean {

        var fName = fontName

        try {

            if (fontName == null) fName = ctx.getString(R.string.barlow_medium)

            val typeface = Typeface.createFromAsset(ctx.assets, fName)

            setTypeface(typeface)

        } catch (e: Exception) {

            myLog("Unable to load typeface: ${e.message}")

            return false
        }

        return true
    }
}