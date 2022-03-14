package com.canliskor.model.fixture

import com.canliskor.model.tournaments.Tournament
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Fixtures(
    @SerializedName("Fixture")
    @Expose
    val fixtures: List<Fixture>,

    @SerializedName("Status")
    @Expose
    val status: Boolean,

    @SerializedName("Tournament")
    @Expose
    val tournament: Tournament
)