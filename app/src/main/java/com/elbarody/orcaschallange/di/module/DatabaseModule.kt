package com.elbarody.orcaschallange.di.module

import com.elbarody.orcaschallange.data.locale.room.PLTeamsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {
    single { PLTeamsDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<PLTeamsDatabase>().matchesDoa() }
}