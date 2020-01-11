package com.elbarody.orcaschallange.app

import android.app.Application
import com.elbarody.orcaschallange.di.module.databaseModule
import com.elbarody.orcaschallange.di.module.plTeamsViewModel
import com.elbarody.orcaschallange.di.module.networkModule
import org.koin.android.ext.android.startKoin

class PLTeamsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(databaseModule, networkModule, plTeamsViewModel))
    }
}