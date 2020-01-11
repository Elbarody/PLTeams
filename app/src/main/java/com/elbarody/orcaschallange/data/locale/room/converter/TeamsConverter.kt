package com.elbarody.orcaschallange.data.locale.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.elbarody.orcaschallange.data.model.Player


class TeamsConverter {

    @TypeConverter
    fun stringToPlayer(data: String): ArrayList<Player> {

        val listType = object : TypeToken<ArrayList<Player>>() {}.type

        return Gson().fromJson(data, listType)
    }


    @TypeConverter
    fun playerToString(player: List<Player>): String {
        return Gson().toJson(player)
    }
}