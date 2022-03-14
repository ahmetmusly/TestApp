package com.canliskor.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


object CSGeneral {

    fun Activity.fullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            if (this.window.insetsController != null) {

                val insetsController = this.window.insetsController

                if (insetsController != null) {

                    insetsController.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())

                    insetsController.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            }
        } else {

            this.window.setFlags(

                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,

                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }


    fun Activity.makeStatusBarTransparent() {

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        window.statusBarColor = Color.TRANSPARENT

        setOrClearLightStatusBar(true)
    }

    fun Activity.updateStatusBarColor(
        color: Int
    ) { // Color must be in hexadecimal format

        setOrClearLightStatusBar(color == Color.BLACK)

        window.statusBarColor = color
    }

    private fun Activity.setOrClearLightStatusBar(isSet: Boolean) {

        val decorView: View = window.decorView

        val wic = WindowInsetsControllerCompat(window, decorView)

        wic.isAppearanceLightStatusBars = isSet // true or false as desired.
    }
}