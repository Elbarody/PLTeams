package com.helwa.orcaschallange.data.locale.room.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.helwa.orcaschallange.data.model.TeamEntityItem
import com.helwa.orcaschallange.data.model.TeamItem

@Dao
interface TeamsDoa {

    @Query("SELECT * from team_entity where id =:id LIMIT 1")
    fun getOneTeam(id: Int): TeamEntityItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneTime(teamEntityItem: TeamEntityItem)

    @Query("SELECT * FROM teams ORDER BY id ASC LIMIT :limit OFFSET :offset ")
    fun getAllTeams(limit: Int, offset: Int): List<TeamItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(vararg teamsItem: TeamItem)

    @get:Query("SELECT * FROM teams WHERE isFavourite = 1 ORDER BY id ASC")
    val getFavouriteTeams: List<TeamItem>
}