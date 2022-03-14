package com.canliskor.model.match_detail

import com.canliskor.model.Period
import com.canliskor.model.Team
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Match(
    @SerializedName("AwayScore")
    @Expose
    val awayScore: Int,

    @SerializedName("AwayTeam")
    @Expose
    val awayTeam: Team,

    @SerializedName("Channel")
    @Expose
    val channel: Team,

    @SerializedName("HomeScore")
    @Expose
    val homeScore: Int,

    @SerializedName("HomeTeam")
    @Expose
    val homeTeam: Team,

    @SerializedName("Id")
    @Expose
    val id: Int,

    @SerializedName("LiveCoverage")
    @Expose
    val liveCoverage: Boolean,

    @SerializedName("MatchTime")
    @Expose
    val matchTime: String,

    @SerializedName("Periods")
    @Expose
    val periods: List<Period>,

    @SerializedName("ScheduledDate")
    @Expose
    val scheduledDate: String
) : Serializable