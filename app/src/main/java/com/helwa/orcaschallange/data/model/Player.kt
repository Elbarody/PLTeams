package com.helwa.orcaschallange.data.model

import com.google.gson.annotations.SerializedName

data class Player(

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("position")
    val position: String? = "",

    @field:SerializedName("nationality")
    val nationality: String? = ""
)