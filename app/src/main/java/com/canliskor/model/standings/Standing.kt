package com.canliskor.model.standings

import com.canliskor.model.Matches
import com.canliskor.model.Team
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Standing(
    @SerializedName("Change")
    @Expose
    val change: Int,

    @SerializedName("CurrentOutcome")
    @Expose
    val currentOutcome: String,

    @SerializedName("Draw")
    @Expose
    val draw: Int,

    @SerializedName("Form")
    @Expose
    val form: Double,

    @SerializedName("GoalsAverage")
    @Expose
    val goalsAverage: Int,

    @SerializedName("GoalsConceded")
    @Expose
    val goalsConceded: Int,

    @SerializedName("GoalsScored")
    @Expose
    val goalsScored: Int,

    @SerializedName("LastMatches")
    @Expose
    val lastMatches: List<Matches>,

    @SerializedName("Lost")
    @Expose
    val lost: Int,

    @SerializedName("Played")
    @Expose
    val played: Int,

    @SerializedName("Points")
    @Expose
    val points: Int,

    @SerializedName("Rank")
    @Expose
    val rank: Int,

    @SerializedName("Team")
    @Expose
    val team: Team,

    @SerializedName("Win")
    @Expose
    val win: Int
)