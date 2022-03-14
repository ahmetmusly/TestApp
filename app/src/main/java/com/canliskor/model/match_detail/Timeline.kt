package com.canliskor.model.match_detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Timeline(
    @SerializedName("Commentary")
    @Expose
    val commentary: String,

    @SerializedName("Icons")
    @Expose
    val icons: Icons,

    @SerializedName("MatchTime")
    @Expose
    val matchTime: Int,

    @SerializedName("Team")
    @Expose
    val team: String,

    @SerializedName("Type")
    @Expose
    val type: Int,

    @SerializedName("TypeStr")
    @Expose
    val typeStr: String
)