package com.helwa.orcaschallange.app

import android.app.Application
import com.helwa.orcaschallange.di.module.databaseModule
import com.helwa.orcaschallange.di.module.plTeamsViewModel
import com.helwa.orcaschallange.di.module.networkModule
import org.koin.android.ext.android.startKoin

class PLTeamsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(databaseModule, networkModule, plTeamsViewModel))
    }
}