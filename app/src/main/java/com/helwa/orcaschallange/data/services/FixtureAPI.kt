package com.helwa.orcaschallange.data.services

import com.helwa.orcaschallange.data.services.response.FixtureResponse
import com.helwa.orcaschallange.data.services.response.TeamDetailsResponse
import com.helwa.orcaschallange.BuildConfig
import com.helwa.orcaschallange.data.model.TeamEntityItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FixtureAPI {
    @GET("/v2/competitions/2021/teams")
    fun getTeams(@Header("X-Auth-Token") apiKey: String = BuildConfig.API_KEY): Observable<FixtureResponse>

    @GET("/v2/teams/{id}")
    fun getSpicialTeam(@Header("X-Auth-Token") apiKey: String = BuildConfig.API_KEY, @Path("id") teamId: Int): Observable<TeamEntityItem>
}