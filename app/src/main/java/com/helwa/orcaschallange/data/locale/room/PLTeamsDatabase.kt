package com.helwa.orcaschallange.data.locale.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.helwa.orcaschallange.data.locale.room.doa.TeamsDoa
import com.helwa.orcaschallange.data.model.TeamEntityItem
import com.helwa.orcaschallange.data.model.TeamItem
import com.helwa.orcaschallange.utils.Constants.DB_NAME
import com.helwa.orcaschallange.utils.Constants.DB_VERSION

@Database(entities = [TeamItem::class, TeamEntityItem::class], version = DB_VERSION , exportSchema = false)
abstract class PLTeamsDatabase : RoomDatabase() {
    abstract fun matchesDoa(): TeamsDoa

    companion object {
        @Volatile
        var INSTANCE: PLTeamsDatabase? = null

        fun getInstance(context: Context): PLTeamsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, PLTeamsDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }


}