package com.helwa.orcaschallange.di.module

import com.helwa.orcaschallange.ui.fixture.FixtureViewModel
import com.helwa.orcaschallange.ui.teamDetails.TeamDetailsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val plTeamsViewModel = module {
    viewModel { FixtureViewModel(get(), get()) }
    viewModel { TeamDetailsViewModel(get(), get()) }
}