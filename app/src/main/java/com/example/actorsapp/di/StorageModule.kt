package com.example.actorsapp.di

import android.app.Application
import com.example.actorsapp.Data.ActorsDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule= module {
    single { provideDatabase(get()) }
}


fun provideDatabase(application: Application): ActorsDataBase {
    return ActorsDataBase.getInstance(application)
}