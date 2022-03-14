package com.example.canliskorapp.interfaces


import com.canliskor.model.fixture.Fixtures
import com.canliskor.model.match_detail.MatchDetail
import com.canliskor.model.standings.Standings
import com.canliskor.model.today_matches.TodayMatches
import com.canliskor.model.tournaments.Tournaments
import retrofit2.Call
import retrofit2.http.*

import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST





interface APIService {

    @POST("/sport/today-matches")
    @FormUrlEncoded
    fun getData(
        @Header("Content-Type") ContentType: String,
        @Field(value = "Token") Token: String

    ): Call<TodayMatches>

    @POST("/sport/fixture")
    @FormUrlEncoded
    fun getDataFixture(
        @Header("Content-Type") ContentType: String,
        @Field(value = "Token") Token: String,
        @Field("tournamentId") tournamentId: Int,
        @Field("seasonId") seasonId: Int?,

        ): Call<Fixtures>

    @POST("/sport/standings")
    @FormUrlEncoded
    fun getDataStandings(
        @Header("Content-Type") ContentType: String,
        @Field(value = "Token") Token: String,
        @Field("tournamentId") tournamentId: Int,

    ): Call<Standings>

    @POST("/sport/match")
    @FormUrlEncoded
    fun getMatchDetail(
        @Header("Content-Type") ContentType: String,
        @Field(value = "Token") Token: String,
        @Field("matchId") matchId: Int,

        ): Call<MatchDetail>

    @POST("/sport/tournaments")
    @FormUrlEncoded
    fun getDataTournaments(
        @Header("Content-Type") ContentType: String,
        @Field(value = "Token") Token: String,
        //@Field("countryId") countryId: Int,

        ): Call<Tournaments>
}