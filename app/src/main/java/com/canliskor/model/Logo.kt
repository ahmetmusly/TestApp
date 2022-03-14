package com.canliskor.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Logo(
    @SerializedName("Big")
    @Expose
    val big: String,

    @SerializedName("Medium")
    @Expose
    val medium: String,

    @SerializedName("Small")
    @Expose
    val small: String
)