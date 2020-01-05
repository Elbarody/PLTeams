package com.helwa.orcaschallange.data.services.response

import com.google.gson.annotations.SerializedName
import com.helwa.orcaschallange.data.model.TeamItem

data class FixtureResponse(
	@field:SerializedName("teams") val teams: List<TeamItem> = emptyList()
)