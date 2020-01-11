package com.elbarody.orcaschallange.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "teams")
data class TeamItem(

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

    var isFavourite: Boolean = false
) {
    companion object {
        val dateComparator: Comparator<TeamItem> =
            Comparator { match, to -> match.id!!.compareTo(to.id!!) }
    }
}