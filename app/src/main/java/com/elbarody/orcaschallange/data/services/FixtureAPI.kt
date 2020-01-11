package com.elbarody.orcaschallange.data.services

import com.elbarody.orcaschallange.data.services.response.FixtureResponse
import com.elbarody.orcaschallange.BuildConfig
import com.elbarody.orcaschallange.data.model.TeamEntityItem
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