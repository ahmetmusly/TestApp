package com.canliskor.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Period(
    @SerializedName("AwayScore")
    @Expose
    val awayScore: Int,

    @SerializedName("HomeScore")
    @Expose
    val homeScore: Int,

    @SerializedName("Period")
    @Expose
    val period: Int,

    @SerializedName("Type")
    @Expose
    val type: Int
)