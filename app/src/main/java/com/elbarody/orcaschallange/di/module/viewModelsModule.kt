package com.elbarody.orcaschallange.di.module

import com.elbarody.orcaschallange.ui.fixture.AllTeamsViewModel
import com.elbarody.orcaschallange.ui.teamDetails.TeamDetailsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val plTeamsViewModel = module {
    viewModel { AllTeamsViewModel(get(), get()) }
    viewModel { TeamDetailsViewModel(get(), get()) }
}