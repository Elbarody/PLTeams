package com.elbarody.orcaschallange.data.model

import com.google.gson.annotations.SerializedName

data class Team(

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("shortName")
    val shortName: String? = "",

    @field:SerializedName("crestUrl")
    val crestUrl: String? = "",

    @field:SerializedName("address")
    val address: String? = "",

    @field:SerializedName("phone")
    val phone: String? = "",

    @field:SerializedName("website")
    val website: String? = "",

    @field:SerializedName("founded")
    val founded: Int? = 0,

    @field:SerializedName("clubColors")
    val clubColors: String? = ""
)