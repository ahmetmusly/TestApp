package com.canliskor.utils


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


object CSInterfaces {
    /**
     *  {} -> implementation optional func
     */

    interface OnClickListener {
        fun it(data: Any?, position: Int? = 0) {}
    }

    interface OnTouch {
        fun userInputEnabled(enabled: Boolean?) {}
    }
}