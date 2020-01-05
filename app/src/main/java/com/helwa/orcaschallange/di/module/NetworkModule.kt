package com.helwa.orcaschallange.di.module

import com.helwa.orcaschallange.data.services.FixtureAPI
import com.helwa.orcaschallange.di.provider.NetworkProvider
import org.koin.dsl.module.module
import retrofit2.Retrofit

val networkModule = module {
    single { NetworkProvider.provideRetrofitInterface() }
    single { get<Retrofit>().create(FixtureAPI::class.java) }
}