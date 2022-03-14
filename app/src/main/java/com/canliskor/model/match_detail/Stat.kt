package com.canliskor.model.match_detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Stat(
    @SerializedName("AwayRatio")
    @Expose
    val awayRatio: Double,

    @SerializedName("AwayValue")
    @Expose
    val awayValue: Int,

    @SerializedName("HomeRatio")
    @Expose
    val homeRatio: Double,

    @SerializedName("HomeValue")
    @Expose
    val homeValue: Int,

    @SerializedName("Text")
    @Expose
    val text: String,

    @SerializedName("Type")
    @Expose
    val type: String
)