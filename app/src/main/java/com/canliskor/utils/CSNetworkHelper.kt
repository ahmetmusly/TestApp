package com.canliskor.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


@Singleton
class CSNetworkHelper @Inject constructor(@ApplicationContext private val context: Context) {

    @Suppress("DEPRECATION")
    fun isNetworkConnected(): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = cm.activeNetwork ?: return false

        val activeNetwork = cm.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

            else -> false
        }
    }
}