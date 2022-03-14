package com.canliskor.model.tournaments

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Tournaments(
    @SerializedName("Status")
    @Expose
    val status: Boolean,

    @SerializedName("Tournaments")
    @Expose
    val tournaments: List<Tournament>
)