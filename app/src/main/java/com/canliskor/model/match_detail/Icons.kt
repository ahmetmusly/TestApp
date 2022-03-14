package com.canliskor.model.match_detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Icons(
    @SerializedName("I_20")
    @Expose
    val i20: String,

    @SerializedName("I_30")
    @Expose
    val i30: String,

    @SerializedName("I_40")
    @Expose
    val i40: String,

    @SerializedName("I_50")
    @Expose
    val i50: String
)