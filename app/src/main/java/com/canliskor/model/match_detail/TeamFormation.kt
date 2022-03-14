package com.canliskor.model.match_detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class TeamFormation(
    @SerializedName("FirstXI")
    @Expose
    val firstXI: List<Sub>,

    @SerializedName("Subs")
    @Expose
    val subs: List<Sub>,

    @SerializedName("Title")
    @Expose
    val title: String
)