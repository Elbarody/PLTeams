package com.elbarody.orcaschallange.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.elbarody.orcaschallange.data.locale.room.converter.TeamsConverter

@Entity(tableName = "team_entity")
@TypeConverters(TeamsConverter::class)
data class TeamEntityItem(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("name")
    val name: String? = "",

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
    val clubColors: String? = "",

    @field:SerializedName("venue")
    val venue: String? = "",

    @field:SerializedName("email")
    val email: String? = "",

    @field:SerializedName("squad")
    val squad: ArrayList<Player>? = null

) {
    companion object {
        val dateComparator: Comparator<TeamEntityItem> =
            Comparator { match, to -> match.id!!.compareTo(to.id!!) }
    }
}