package com.canliskor.model.standings

import com.canliskor.model.tournaments.Tournament
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Standings(
    @SerializedName("Groups")
    @Expose
    val groups: List<Group>,

    @SerializedName("Status")
    @Expose
    val status: Boolean,

    @SerializedName("Tournament")
    @Expose
    val tournament: Tournament
) : Serializable