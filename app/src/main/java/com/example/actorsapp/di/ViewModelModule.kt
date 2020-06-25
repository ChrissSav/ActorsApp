package com.example.actorsapp.di

import com.example.actorsapp.UI.Actors.ActorsViewModel
import com.example.actorsapp.UI.Details.DetailsViewModel
import com.example.actorsapp.UI.Favorites.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { FavoritesViewModel(get()) }
    viewModel { ActorsViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get()) }
}

