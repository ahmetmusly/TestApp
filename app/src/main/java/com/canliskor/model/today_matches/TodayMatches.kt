package com.canliskor.model.today_matches

import com.canliskor.model.tournaments.Tournament
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class TodayMatches(
    @SerializedName("Status")
    @Expose
    val status: Boolean,

    @SerializedName("Tournaments")
    @Expose
    val tournaments: List<Tournament>
)



