package com.canliskor.model.tournaments

import com.canliskor.model.Logo
import com.canliskor.model.Matches
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


data class Tournament(
    @SerializedName("Country")
    @Expose
    val country: Country,

    @SerializedName("CurrentSeason")
    @Expose
    val currentSeason: CurrentSeason,

    @SerializedName("SeasonName")
    @Expose
    val seasonName: String,

    @SerializedName("Id")
    @Expose
    val id: Int,

    @SerializedName("IsCompare")
    @Expose
    val isCompare: Boolean,

    @SerializedName("IsCup")
    @Expose
    val isCup: Boolean,

    @SerializedName("Name")
    @Expose
    val name: String,

    @SerializedName("CountryLogos")
    @Expose
    val countryLogos: Logo,

    @SerializedName("CountryName")
    @Expose
    val countryName: String,

    @SerializedName("Matches")
    @Expose
    val matches: List<Matches>,

    @SerializedName("TournamentId")
    @Expose
    val tournamentId: Int,

    @SerializedName("TournamentName")
    @Expose
    val tournamentName: String
)