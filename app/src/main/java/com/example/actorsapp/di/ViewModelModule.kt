package com.example.actorsapp.di

import com.example.actorsapp.UI.Actors.ActorsViewModel
import com.example.actorsapp.UI.Details.DetailsViewModel
import com.example.actorsapp.UI.Favorites.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModuleFav = module {
    viewModel {
        FavoritesViewModel(get())
    }
}

val viewModelModuleActor = module {
    viewModel {
        ActorsViewModel(get(), get())
    }
}

val viewModelModuleDetails = module {
    viewModel {
        DetailsViewModel(get(), get())
    }
}