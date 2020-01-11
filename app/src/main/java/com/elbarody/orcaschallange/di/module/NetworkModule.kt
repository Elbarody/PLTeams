package com.elbarody.orcaschallange.di.module

import com.elbarody.orcaschallange.data.services.FixtureAPI
import com.elbarody.orcaschallange.di.provider.NetworkProvider
import org.koin.dsl.module.module
import retrofit2.Retrofit

val networkModule = module {
    single { NetworkProvider.provideRetrofitInterface() }
    single { get<Retrofit>().create(FixtureAPI::class.java) }
}