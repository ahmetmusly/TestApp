package com.canliskor.repository

import com.canliskor.api.ApiHelper
import javax.inject.Inject


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


class CSMainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getDataTodayMatches(token: String) =
        apiHelper.getDataTodayMatches(token = token)

    suspend fun getDataFixture(token: String, tournamentId: Int, seasonId: Int?) =
        apiHelper.getDataFixture(token = token, tournamentId = tournamentId, seasonId = seasonId)

    suspend fun getDataStandings(token: String, tournamentId: Int) =
        apiHelper.getDataStandings(token = token, tournamentId = tournamentId)

    suspend fun getMatchDetail(token: String, matchId: Int) =
        apiHelper.getMatchDetail(token = token, matchId = matchId)

    suspend fun getDataTournaments(token: String) =
        apiHelper.getDataTournaments(token = token)
}