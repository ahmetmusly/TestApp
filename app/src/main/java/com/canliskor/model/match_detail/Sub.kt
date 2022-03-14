package com.canliskor.model.match_detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Sub(
    @SerializedName("JerseyNumber")
    @Expose
    val jerseyNumber: Int,

    @SerializedName("PlayerName")
    @Expose
    val playerName: String
)