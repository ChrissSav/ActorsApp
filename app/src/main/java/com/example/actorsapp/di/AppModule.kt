package com.example.actorsapp.di

import android.app.Application
import android.content.Context
import com.example.actorsapp.API.ClientAPI
import com.example.actorsapp.API.MoviesEndpoints
import com.example.actorsapp.Data.ActorsDataBase
import org.koin.dsl.module


val myModules = module {

    single { ClientAPI() }


}
val testModule = module {
    single { provideEndpoints() }
}

fun provideEndpoints(): MoviesEndpoints {
    return ClientAPI.createService(MoviesEndpoints::class.java)
}