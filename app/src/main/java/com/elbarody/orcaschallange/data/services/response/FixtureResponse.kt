package com.elbarody.orcaschallange.data.services.response

import com.google.gson.annotations.SerializedName
import com.elbarody.orcaschallange.data.model.TeamItem

data class FixtureResponse(
	@field:SerializedName("teams") val teams: List<TeamItem> = emptyList()
)