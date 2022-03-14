package com.canliskor.api

import com.canliskor.utils.CSConstants.Companion.CONTENT_TYPE
import com.canliskor.model.fixture.Fixtures
import com.canliskor.model.match_detail.MatchDetail
import com.canliskor.model.standings.Standings
import com.canliskor.model.today_matches.TodayMatches
import com.canliskor.model.tournaments.Tournaments
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


interface ApiService {

    @POST("/sport/today-matches")
    @FormUrlEncoded
    suspend fun getDataTodayMatches(
        @Header("Content-Type") ContentType: String = CONTENT_TYPE,
        @Field(value = "Token") token: String
    ): Response<TodayMatches>


    @POST("/sport/fixture")
    @FormUrlEncoded
    suspend fun getDataFixture(
        @Header("Content-Type") ContentType: String = CONTENT_TYPE,
        @Field(value = "Token") token: String,
        @Field("tournamentId") tournamentId: Int,
        @Field("seasonId") seasonId: Int?,
    ): Response<Fixtures>


    @POST("/sport/standings")
    @FormUrlEncoded
    suspend fun getDataStandings(
        @Header("Content-Type") ContentType: String = CONTENT_TYPE,
        @Field(value = "Token") token: String,
        @Field(value = "tournamentId") tournamentId: Int,
    ): Response<Standings>


    @POST("/sport/match")
    @FormUrlEncoded
    suspend fun getMatchDetail(
        @Header("Content-Type") ContentType: String = CONTENT_TYPE,
        @Field(value = "Token") token: String,
        @Field("matchId") matchId: Int,
    ): Response<MatchDetail>


    @POST("/sport/tournaments")
    @FormUrlEncoded
    suspend fun getDataTournaments(
        @Header("Content-Type") ContentType: String = CONTENT_TYPE,
        @Field(value = "Token") token: String,
        //@Field("countryId") countryId: Int,
    ): Response<Tournaments>
}