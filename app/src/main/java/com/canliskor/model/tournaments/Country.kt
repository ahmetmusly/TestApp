package com.canliskor.model.tournaments

import com.canliskor.model.Logo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Country(
    @SerializedName("Id")
    @Expose
    val id: Int,

    @SerializedName("Logo")
    @Expose
    val logo: Logo,

    @SerializedName("Name")
    @Expose
    val name: String
)