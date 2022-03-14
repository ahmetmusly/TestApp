package com.canliskor.utils


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


class CSExtras {

    private var _onClick: CSInterfaces.OnClickListener? = null

    fun setOnclick(onClick: CSInterfaces.OnClickListener?): CSExtras {

        this._onClick = onClick

        return this
    }

    fun onclick() = _onClick


    private var _onTouch: CSInterfaces.OnTouch? = null

    fun setOnTouch(onTouch: CSInterfaces.OnTouch?): CSExtras {

        this._onTouch = onTouch

        return this
    }

    fun onTouch() = _onTouch
}