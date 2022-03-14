package com.canliskor.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.canliskor.utils.Extensions
import com.canliskor.utils.CSPreferenceHelper.get
import com.canliskor.utils.CSPreferenceHelper.savePrefs
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */

@HiltAndroidApp
class LiveScoreApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val isDarkMode = savePrefs()["isDark", false]
//        changeTheme(isDarkMode)
        Extensions.myLog("$isDarkMode")
//        val mode = if (isDark) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
//        AppCompatDelegate.setDefaultNightMode(mode)
    }

    fun changeTheme(isDarkMode: Boolean) {
        val mode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}