package com.canliskor.api

import javax.inject.Inject


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getDataTodayMatches(token: String) =
        apiService.getDataTodayMatches(token = token)

    override suspend fun getDataFixture(token: String, tournamentId: Int, seasonId: Int?) =
        apiService.getDataFixture(token = token, tournamentId = tournamentId, seasonId = seasonId)

    override suspend fun getDataStandings(token: String, tournamentId: Int) =
        apiService.getDataStandings(token = token, tournamentId = tournamentId)

    override suspend fun getMatchDetail(token: String, matchId: Int) =
        apiService.getMatchDetail(token = token, matchId = matchId)

    override suspend fun getDataTournaments(token: String) =
        apiService.getDataTournaments(token = token)

}