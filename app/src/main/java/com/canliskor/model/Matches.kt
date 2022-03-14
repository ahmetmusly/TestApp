package com.canliskor.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Matches(
    @SerializedName("AwayScore")
    @Expose
    val awayScore: Int,

    @SerializedName("AwayTeam")
    @Expose
    val awayTeam: Team,

    @SerializedName("Channel")
    @Expose
    val channel: Any? = null,

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
    val scheduledDate: String,

    @SerializedName("AwayScoreExtra")
    @Expose
    val awayScoreExtra: Any,

    @SerializedName("HomeScoreExtra")
    @Expose
    val homeScoreExtra: Any,

    @SerializedName("Type")
    @Expose
    val type: String

) : Serializable