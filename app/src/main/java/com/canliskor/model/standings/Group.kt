package com.canliskor.model.standings

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Group(
    @SerializedName("AwayStandings")
    @Expose
    val awayStandings: List<Standing>,

    @SerializedName("HomeStandings")
    @Expose
    val homeStandings: List<Standing>,

    @SerializedName("Standings")
    @Expose
    val standings: List<Standing>,

    @SerializedName("Name")
    @Expose
    val name: Any,

    @SerializedName("TieBreakRule")
    @Expose
    val tieBreakRule: String
):Serializable