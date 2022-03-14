package com.canliskor.api

import com.canliskor.model.fixture.Fixtures
import com.canliskor.model.match_detail.MatchDetail
import com.canliskor.model.standings.Standings
import com.canliskor.model.today_matches.TodayMatches
import com.canliskor.model.tournaments.Tournaments
import retrofit2.Response


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


interface ApiHelper {

    suspend fun getDataTodayMatches(token: String):
            Response<TodayMatches>

    suspend fun getDataFixture(token: String, tournamentId: Int, seasonId: Int?):
            Response<Fixtures>

    suspend fun getDataStandings(token: String, tournamentId: Int):
            Response<Standings>

    suspend fun getMatchDetail(token: String, matchId: Int):
            Response<MatchDetail>

    suspend fun getDataTournaments(token: String):
            Response<Tournaments>
}