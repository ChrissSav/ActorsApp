package com.example.actorsapp.di

import com.example.actorsapp.API.Api
import com.example.actorsapp.repository.ApiRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val appModule = module {
    single { provideMoshi() }
}


fun provideMoshi(): Moshi =
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

