package com.canliskor.utils

import android.annotation.SuppressLint
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


object CSDateUtils {
    fun getDateOld(dateValue: String): String? {
        val nowTime = Calendar.getInstance()
        val neededTime = Calendar.getInstance()
        neededTime.timeInMillis = dateToMillis(dateValue)
        return if (neededTime[Calendar.YEAR] == nowTime[Calendar.YEAR]) {
            if (neededTime[Calendar.MONTH] == nowTime[Calendar.MONTH]) {
                when {
                    neededTime[Calendar.DATE] - nowTime[Calendar.DATE] == 1 -> {
                        //here return like "Tomorrow at 12:00"
                        "Tomorrow at " + DateFormat.format("HH:mm:ss", neededTime)
                    }
                    nowTime[Calendar.DATE] == neededTime[Calendar.DATE] -> {
                        //here return like "Today at 12:00"
                        "Today at " + DateFormat.format("HH:mm:ss", neededTime)
                    }
                    nowTime[Calendar.DATE] - neededTime[Calendar.DATE] == 1 -> {
                        //here return like "Yesterday at 12:00"
                        "Yesterday at " + DateFormat.format("HH:mm:ss", neededTime)
                    }
                    else -> {
                        //here return like "May 31, 12:00"
                        DateFormat.format("MMMM d, HH:mm:ss", neededTime).toString()
                    }
                }
            } else {
                //here return like "May 31, 12:00"
                DateFormat.format("MMMM d, HH:mm:ss", neededTime).toString()
            }
        } else {
            //here return like "May 31 2010, 12:00" - it's a different year we need to show it
            DateFormat.format("MMMM dd yyyy, HH:mm:ss", neededTime).toString()
        }
    }

    fun getDate(dateValue: String): String? {
        val nowTime = Calendar.getInstance()
        val neededTime = Calendar.getInstance()
        neededTime.timeInMillis = dateToMillis(dateValue)
        return if (neededTime[Calendar.YEAR] == nowTime[Calendar.YEAR]) {
            if (neededTime[Calendar.MONTH] == nowTime[Calendar.MONTH]) {
                when {
                    neededTime[Calendar.DATE] - nowTime[Calendar.DATE] == 1 ->
                        "Yarın " + DateFormat.format("HH:mm", neededTime)
                    nowTime[Calendar.DATE] == neededTime[Calendar.DATE] ->
                        "Bugün " + DateFormat.format("HH:mm", neededTime)
                    nowTime[Calendar.DATE] - neededTime[Calendar.DATE] == 1 ->
                        "Dün " + DateFormat.format("HH:mm", neededTime)
                    else ->
                        DateFormat.format("MMMM d, HH:mm", neededTime).toString()
                }
            } else {
                DateFormat.format("MMMM d, HH:mm", neededTime).toString()
            }
        } else {
            DateFormat.format("MMMM dd yyyy, HH:mm", neededTime).toString()
        }
    }


    //        val DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    @SuppressLint("SimpleDateFormat")
    fun dateToMillis(dateValue: String): Long {
        val formatPattern = "yyyy-MM-dd'T'HH:mm:ss"
        val formatter = SimpleDateFormat(formatPattern)
        formatter.isLenient = false
        val formattedDate: Date = formatter.parse(dateValue)
        return formattedDate.time
    }
}