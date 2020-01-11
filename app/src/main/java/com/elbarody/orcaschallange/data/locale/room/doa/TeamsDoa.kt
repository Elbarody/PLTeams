package com.elbarody.orcaschallange.data.locale.room.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elbarody.orcaschallange.data.model.TeamEntityItem
import com.elbarody.orcaschallange.data.model.TeamItem
import io.reactivex.Single

@Dao
interface TeamsDoa {

    @Query("SELECT * from team_entity where id =:id")
    fun getOneTeam(id: Int): Single<TeamEntityItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneTime(teamEntityItem: TeamEntityItem)

    @get:Query("SELECT * FROM teams ORDER BY id ASC")
    val getAllTeams: List<TeamItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(vararg teamsItem: TeamItem)

    @get:Query("SELECT * FROM teams WHERE isFavourite = 1 ORDER BY id ASC")
    val getFavouriteTeams: List<TeamItem>
}