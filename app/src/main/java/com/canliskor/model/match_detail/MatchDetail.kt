package com.canliskor.model.match_detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class MatchDetail(
    @SerializedName("AwayTeamFormation")
    @Expose
    val awayTeamFormation: TeamFormation,

    @SerializedName("FunFacts")
    @Expose
    val funFacts: List<Any>,

    @SerializedName("HomeTeamFormation")
    @Expose
    val homeTeamFormation: TeamFormation,

    @SerializedName("Match")
    @Expose
    val match: Match,

    @SerializedName("Stats")
    @Expose
    val stats: List<Stat>,

    @SerializedName("Status")
    @Expose
    val status: Boolean,

    @SerializedName("Timeline")
    @Expose
    val timeline: List<Timeline>,

    @SerializedName("TournamentId")
    @Expose
    val tournamentId: Int,

    @SerializedName("TournamentName")
    @Expose
    val tournamentName: String
)