package com.canliskor.model.fixture

import com.canliskor.model.Matches
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Fixture(
    @SerializedName("IsCurrent")
    @Expose
    val isCurrent: Boolean,

    @SerializedName("Matches")
    @Expose
    val matches: List<Matches>,

    @SerializedName("Week")
    @Expose
    val week: String
)